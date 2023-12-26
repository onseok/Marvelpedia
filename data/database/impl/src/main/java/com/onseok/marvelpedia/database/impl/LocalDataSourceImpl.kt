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
package com.onseok.marvelpedia.database.impl

import com.onseok.marvelpedia.database.LocalDataSource
import com.onseok.marvelpedia.database.impl.dao.MarvelHeroDao
import com.onseok.marvelpedia.database.impl.mapper.toMarvelHero
import com.onseok.marvelpedia.database.impl.mapper.toMarvelHeroEntity
import com.onseok.marvelpedia.model.MarvelHeroModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val marvelHeroDao: MarvelHeroDao,
) : LocalDataSource {
    override suspend fun addFavoriteMarvelHero(marvelHero: MarvelHeroModel) {
        val currentFavoritesCount = marvelHeroDao.getFavoriteMarvelHeroesCount()
        if (currentFavoritesCount >= 5) {
            marvelHeroDao.deleteOldestFavoriteMarvelHero()
        }
        marvelHeroDao.insertFavoriteMarvelHero(marvelHero.toMarvelHeroEntity())
    }

    override suspend fun removeFavoriteMarvelHero(marvelHeroId: Int) {
        marvelHeroDao.deleteFavoriteMarvelHero(marvelHeroId)
    }

    override fun getFavoriteMarvelHeroes(): Flow<List<MarvelHeroModel>> {
        return marvelHeroDao.getFavoriteMarvelHeroes().map {
            it.map { marvelHeroEntity -> marvelHeroEntity.toMarvelHero() }
        }
    }

    override suspend fun isFavoriteMarvelHero(marvelHeroId: Int): Boolean {
        return marvelHeroDao.isFavoriteMarvelHero(marvelHeroId)
    }
}
