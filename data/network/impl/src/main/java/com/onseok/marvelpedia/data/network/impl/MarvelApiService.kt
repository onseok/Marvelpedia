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

import com.onseok.marvelpedia.buildconfig.BuildConfig
import com.onseok.marvelpedia.data.network.response.MarvelResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApiService {

    @GET("characters")
    suspend fun getMarvelHeroes(
        @Query("ts") ts: String,
        @Query("hash") hash: String,
        @Query("apikey") apikey: String = BuildConfig.MARVEL_PUBLIC_KEY,
        @Query("nameStartsWith") nameStartsWith: String,
        @Query("orderBy") orderBy: String = "name",
        @Query("limit") limit: Int = 10,
        @Query("offset") page: Int
    ): MarvelResponse
}