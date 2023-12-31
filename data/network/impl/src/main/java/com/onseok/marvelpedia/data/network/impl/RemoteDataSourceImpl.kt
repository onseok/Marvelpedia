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
package com.onseok.marvelpedia.data.network.impl

import com.onseok.marvelpedia.data.network.RemoteDataSource
import com.onseok.marvelpedia.data.network.response.MarvelResponse
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: MarvelApiService,
) : RemoteDataSource {
    override suspend fun getMarvelHeroes(
        timestamp: String,
        hash: String,
        nameStartsWith: String,
        page: Int
    ): MarvelResponse {
        return apiService.getMarvelHeroes(
            ts = timestamp,
            hash = hash,
            nameStartsWith = nameStartsWith,
            page = page
        )
    }
}
