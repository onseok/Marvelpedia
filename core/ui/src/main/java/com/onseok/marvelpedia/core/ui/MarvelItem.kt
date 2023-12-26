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
package com.onseok.marvelpedia.core.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.onseok.marvelpedia.core.designsystem.theme.MarvelpediaTheme
import com.onseok.marvelpedia.core.imageloading.AsyncImage
import com.onseok.marvelpedia.model.MarvelHeroModel

@Composable
fun MarvelItem(
    marvelHero: MarvelHeroModel,
    onClick: (MarvelHeroModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        color = MarvelpediaTheme.colors.onSurface.copy(alpha = 0.1f),
        shape = MarvelpediaTheme.shapes.medium,
        elevation = 0.dp,
        border = if (marvelHero.isFavorite) {
            BorderStroke(width = 3.dp, color = Color.Red)
        } else {
            null
        },
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomEnd,
        ) {
            AsyncImage(
                model = marvelHero.thumbnailImageUrl,
                contentDescription = marvelHero.name,
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(27 / 40f)
                    .clickable(
                        onClick = { onClick(marvelHero) },
                    ),
                contentScale = ContentScale.Crop,
            )
            Text(
                text = marvelHero.name,
                modifier = Modifier.padding(8.dp),
                style = TextStyle(
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                ),
                overflow = TextOverflow.Ellipsis,
                softWrap = false,
            )
        }
    }
}
