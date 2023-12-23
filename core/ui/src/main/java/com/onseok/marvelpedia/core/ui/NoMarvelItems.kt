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

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.onseok.marvelpedia.core.designsystem.icon.MarvelpediaIcons
import com.onseok.marvelpedia.core.designsystem.theme.MarvelpediaTheme
import com.onseok.marvelpedia.core.resources.R

@Composable
fun NoMarvelItems(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            imageVector = MarvelpediaIcons.ViewModule,
            contentDescription = null,
            colorFilter = ColorFilter.tint(color = MarvelpediaTheme.colors.onBackground),
            modifier = Modifier.size(72.dp),
        )
        Text(
            text = stringResource(R.string.no_marvels_description),
            color = MarvelpediaTheme.colors.onBackground,
        )
    }
}
