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
package com.onseok.marvelpedia.database.impl.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.onseok.marvelpedia.database.impl.entity.MarvelHeroEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MarvelHeroDao {

    @Query("SELECT * FROM marvel_hero")
    fun getFavoriteMarvelHeroes(): Flow<List<MarvelHeroEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteMarvelHero(marvelHero: MarvelHeroEntity)

    @Query("DELETE FROM marvel_hero WHERE id = :marvelHeroId")
    suspend fun deleteFavoriteMarvelHero(marvelHeroId: Int)

    @Query("SELECT COUNT(id) FROM marvel_hero WHERE id = :marvelHeroId")
    suspend fun getCountForFavoriteMarvelHero(marvelHeroId: Int): Int

    suspend fun isFavoriteMarvelHero(marvelHeroId: Int): Boolean {
        return getCountForFavoriteMarvelHero(marvelHeroId) > 0
    }

    @Query("SELECT COUNT(id) FROM marvel_hero")
    suspend fun getFavoriteMarvelHeroesCount(): Int

    @Query("DELETE FROM marvel_hero WHERE id IN (SELECT id FROM marvel_hero ORDER BY added_at ASC LIMIT 1)")
    suspend fun deleteOldestFavoriteMarvelHero()
}