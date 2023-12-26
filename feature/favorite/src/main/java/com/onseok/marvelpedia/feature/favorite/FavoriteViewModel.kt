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
package com.onseok.marvelpedia.feature.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onseok.marvelpedia.common.IoDispatcher
import com.onseok.marvelpedia.data.repository.MarvelRepository
import com.onseok.marvelpedia.model.MarvelHeroModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: MarvelRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _marvelHeroes = MutableStateFlow<List<MarvelHeroModel>>(emptyList())
    val marvelHeroes: StateFlow<List<MarvelHeroModel>> = _marvelHeroes

    init {
        repository.getFavoriteMarvelHeroes()
            .onEach { marvelHeroes ->
                _marvelHeroes.emit(marvelHeroes.sortedBy { it.addedAt })
            }
            .flowOn(ioDispatcher)
            .launchIn(viewModelScope)
    }

    fun onDeleteFavoriteMarvelItem(marvelHeroModel: MarvelHeroModel) {
        viewModelScope.launch {
            repository.removeFavoriteMarvelHero(marvelHeroModel.id)
        }
    }
}