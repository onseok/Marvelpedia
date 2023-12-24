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
import com.onseok.marvelpedia.data.repository.MarvelRepository
import com.onseok.marvelpedia.database.LocalDataSource
import com.onseok.marvelpedia.model.MarvelHeroModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.Date
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : MarvelRepository {
    override suspend fun searchMarvelHeroes(query: String): List<MarvelHeroModel> {
        return withContext(ioDispatcher) {
            val ts = getTimesStamp()
            remoteDataSource.getMarvelHeroes(
                timestamp = ts,
                hash = getTsHash(ts),
                nameStartsWith = query,
            ).data.results.map { it.asModel() }
        }
    }
}

fun getTsHash(ts: String): String {
    return md5(ts + BuildConfig.MARVEL_PRIVATE_KEY + BuildConfig.MARVEL_PUBLIC_KEY)
}

fun getTimesStamp(): String = Date().time.toString()

private fun md5(string: String): String {
    val md5 = "MD5"

    try {
        val digest = MessageDigest.getInstance(md5)
        digest.update(string.toByteArray())
        val messageDigest = digest.digest()

        val hexString = StringBuilder()
        for (aMessageDigest in messageDigest) {
            var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
            while (h.length < 2) {
                h = "0$h"
            }
            hexString.append(h)
        }
        return hexString.toString()

    } catch (e: NoSuchAlgorithmException) {
        e.printStackTrace()
    }

    return ""
}

