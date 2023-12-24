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
package com.onseok.marvelpedia.data.network.util

import kotlinx.datetime.Clock
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

object MarvelApiUtils {
    private const val HASH_TYPE = "MD5"

    fun generateCurrentTimestamp(): String {
        return Clock.System.now().epochSeconds.toString()
    }

    fun generateHash(timestamp: String, privateKey: String, publicKey: String): String {
        return hashString(timestamp + privateKey + publicKey)
    }

    private fun hashString(input: String): String {
        try {
            val bytes = MessageDigest.getInstance(HASH_TYPE)
                .digest(input.toByteArray(Charsets.UTF_8))
            return bytes.joinToString("") { "%02x".format(it) }
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

        return ""
    }
}