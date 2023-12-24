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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: MarvelRepository,
) : ViewModel() {

    private val _selectedMainTab = MutableStateFlow(MainTabUiModel.Search)
    val selectedMainTab: StateFlow<MainTabUiModel> = _selectedMainTab

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    @OptIn(FlowPreview::class)
    val uiModel: StateFlow<SearchUiModel> = _query
        .debounce(300)
        .map { it.trim() }
        .distinctUntilChanged()
        .flatMapLatest { query ->
            flow {
                if (query.length < 2) {
                    emit(SearchUiModel.None)
                } else {
                    emit(SearchUiModel.Loading)
                    try {
                        val marvelHeroes = repository.searchMarvelHeroes(query)
                        emit(
                            SearchUiModel.Success(
                                marvelHeroes = marvelHeroes,
                                hasNoItem = marvelHeroes.isEmpty(),
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
            started = SharingStarted.WhileSubscribed(5_000),
        )

    fun onMainTabSelected(mainTab: MainTabUiModel) {
        viewModelScope.launch {
            _selectedMainTab.emit(mainTab)
        }
    }

    fun onQueryChanged(query: String) {
        viewModelScope.launch {
            _query.emit(query)
        }
    }
}
