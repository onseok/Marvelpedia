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
package com.onseok.marvelpedia.feature.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.onseok.marvelpedia.core.designsystem.icon.MarvelpediaIcons
import com.onseok.marvelpedia.core.designsystem.theme.MarvelpediaTheme
import com.onseok.marvelpedia.resources.R

@Composable
fun SearchScreen(viewModel: SearchViewModel) {
    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        topBar = {
            Surface(
                modifier = Modifier.fillMaxWidth().height(56.dp),
                color = MarvelpediaTheme.colors.primarySurface,
                elevation = AppBarDefaults.TopAppBarElevation,
            ) {
                val focusManager = LocalFocusManager.current
                val handleColor = MarvelpediaTheme.colors.secondary
                val contentAlpha = ContentAlpha.medium
                val customTextSelectionColors = remember(handleColor, contentAlpha) {
                    TextSelectionColors(
                        handleColor = handleColor,
                        backgroundColor = handleColor.copy(alpha = contentAlpha),
                    )
                }
                CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
                    val focusRequester = FocusRequester()
                    LaunchedEffect(Unit) {
                        focusRequester.requestFocus()
                    }

                    val query by viewModel.query.collectAsState()
                    TextField(
                        value = query,
                        onValueChange = { viewModel.onQueryChanged(it) },
                        modifier = Modifier.fillMaxSize()
                            .focusRequester(focusRequester),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Search,
                        ),
                        keyboardActions = KeyboardActions(
                            onSearch = {
                                focusManager.clearFocus()
                            },
                        ),
                        singleLine = true,
                        placeholder = {
                            Text(stringResource(R.string.search_hint))
                        },
                        trailingIcon = {
                            IconButton(onClick = { viewModel.onQueryChanged("") }) {
                                Icon(
                                    imageVector = MarvelpediaIcons.Close,
                                    contentDescription = null,
                                )
                            }
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            cursorColor = MarvelpediaTheme.colors.secondary,
                            backgroundColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            leadingIconColor = MarvelpediaTheme.colors.onSurface,
                            trailingIconColor = MarvelpediaTheme.colors.onSurface,
                        ),
                    )
                }
            }
        },
    ) { paddingValues ->
        // TODO
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            NoMarvelItems(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
private fun NoMarvelItems(
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
