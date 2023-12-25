/*
 * Copyright 2023 onseok
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.onseok.marvelpedia.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onseok.marvelpedia.data.repository.MarvelRepository
import com.onseok.marvelpedia.log.Logger
import com.onseok.marvelpedia.model.MarvelHeroModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: MarvelRepository,
) : ViewModel() {

    companion object {
        private const val PAGE_SIZE = 10
        private const val START_PAGE_INDEX = 1
    }

    private val _marvelHeroes = MutableStateFlow<List<MarvelHeroModel>>(emptyList())
    val marvelHeroes: StateFlow<List<MarvelHeroModel>> = _marvelHeroes

    private val _selectedMainTab = MutableStateFlow(MainTabUiModel.Search)
    val selectedMainTab: StateFlow<MainTabUiModel> = _selectedMainTab

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    private val _currentPage = MutableStateFlow(START_PAGE_INDEX)

    private var lastQuery = ""
    private var lastPage = START_PAGE_INDEX

    val uiModel: StateFlow<SearchUiModel> = _query.combine(_currentPage) { query, page ->
        query to page
    }
        .debounce(300)
        .map { (query, page) ->
            query.trim() to page
        }
        .distinctUntilChanged()
        .flatMapLatest { (query, page) ->
            flow {
                if (query != lastQuery) {
                    emit(SearchUiModel.Loading)
                    lastQuery = query
                } else if (page != lastPage) {
                    emit(SearchUiModel.Paginating)
                }

                lastPage = page

                if (query.length < 2) {
                    emit(SearchUiModel.None)
                    _marvelHeroes.value = emptyList()
                } else {
                    try {
                        val newHeroes = repository.searchMarvelHeroes(query, (page - 1) * PAGE_SIZE + 1)
                        val updatedList = _marvelHeroes.value + newHeroes
                        _marvelHeroes.value = updatedList

                        val hasMore = newHeroes.size >= PAGE_SIZE
                        emit(
                            SearchUiModel.Success(
                                updatedList,
                                hasNoItem = updatedList.isEmpty(),
                                hasMoreItems = hasMore,
                            )
                        )
                    } catch (e: Exception) {
                        emit(SearchUiModel.None)
                    }
                }
            }
        }
        .stateIn(
            scope = viewModelScope,
            initialValue = SearchUiModel.None,
            started = SharingStarted.WhileSubscribed(5_000)
        )

    fun loadNextPage() {
        viewModelScope.launch {
            _currentPage.emit(_currentPage.value + 1)
        }
    }

    fun onMainTabSelected(mainTab: MainTabUiModel) {
        viewModelScope.launch {
            _selectedMainTab.emit(mainTab)
        }
    }

    fun onQueryChanged(query: String) {
        viewModelScope.launch {
            _query.emit(query)
            _currentPage.emit(START_PAGE_INDEX)
        }
    }
}
