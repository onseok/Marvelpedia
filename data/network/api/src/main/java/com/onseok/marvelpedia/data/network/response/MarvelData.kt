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
package com.onseok.marvelpedia.data.network.response

import com.onseok.marvelpedia.model.MarvelHeroModel
import kotlinx.datetime.Clock
import kotlinx.serialization.Serializable

@Serializable
data class MarvelData(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<Character>,
)

@Serializable
data class Character(
    val id: Int,
    val name: String,
    val description: String,
    val modified: String,
    val thumbnail: Image,
    val resourceURI: String? = null,
    val comics: ComicList,
    val series: SeriesList,
    val stories: StoryList,
    val events: EventList,
    val urls: List<Url>,
)

fun Character.asModel(): MarvelHeroModel {
    return MarvelHeroModel(
        id = id,
        name = name,
        thumbnailImageUrl = listOfNotNull(
            thumbnail.path,
            thumbnail.extension,
        ).joinToString(separator = "."),
        addedAt = Clock.System.now().epochSeconds
    )
}

@Serializable
data class Image(
    val path: String,
    val extension: String,
)

@Serializable
data class ComicList(
    val available: Int,
    val returned: Int,
    val collectionURI: String? = null,
    val items: List<ComicSummary>,
) {
    @Serializable
    data class ComicSummary(
        val resourceURI: String? = null,
        val name: String,
    )
}

@Serializable
data class SeriesList(
    val available: Int,
    val returned: Int,
    val collectionURI: String? = null,
    val items: List<SeriesSummary>,
) {
    @Serializable
    data class SeriesSummary(
        val resourceURI: String? = null,
        val name: String,
    )
}

@Serializable
data class Url(
    val type: String,
    val url: String? = null,
)

@Serializable
data class StoryList(
    val available: Int,
    val returned: Int,
    val collectionURI: String? = null,
    val items: List<StorySummary>,
) {
    @Serializable
    data class StorySummary(
        val resourceURI: String? = null,
        val name: String,
        val type: String,
    )
}

@Serializable
data class EventList(
    val available: Int,
    val returned: Int,
    val collectionURI: String? = null,
    val items: List<EventSummary>,
) {
    @Serializable
    data class EventSummary(
        val resourceURI: String? = null,
        val name: String,
    )
}
