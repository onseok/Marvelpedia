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
package com.onseok.marvelpedia.data.repository.impl

import com.onseok.marvelpedia.buildconfig.BuildConfig
import com.onseok.marvelpedia.common.IoDispatcher
import com.onseok.marvelpedia.data.network.RemoteDataSource
import com.onseok.marvelpedia.data.network.response.asModel
import com.onseok.marvelpedia.data.network.util.MarvelApiUtils
import com.onseok.marvelpedia.data.repository.MarvelRepository
import com.onseok.marvelpedia.database.LocalDataSource
import com.onseok.marvelpedia.model.MarvelHeroModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : MarvelRepository {
    override suspend fun searchMarvelHeroes(query: String, page: Int): List<MarvelHeroModel> {
        return withContext(ioDispatcher) {
            val ts = MarvelApiUtils.generateCurrentTimestamp()
            val hash = MarvelApiUtils.generateHash(
                ts,
                BuildConfig.MARVEL_PRIVATE_KEY,
                BuildConfig.MARVEL_PUBLIC_KEY,
            )

            remoteDataSource.getMarvelHeroes(
                timestamp = ts,
                hash = hash,
                nameStartsWith = query,
                page = page,
            ).data.results.map { it.asModel() }
        }
    }
}
