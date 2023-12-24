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

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.CircularProgressIndicator
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.onseok.marvelpedia.core.designsystem.icon.MarvelpediaIcons
import com.onseok.marvelpedia.core.designsystem.theme.MarvelpediaTheme
import com.onseok.marvelpedia.core.imageloading.AsyncImage
import com.onseok.marvelpedia.core.resources.R
import com.onseok.marvelpedia.core.ui.NoMarvelItems
import com.onseok.marvelpedia.model.MarvelHeroModel

@Composable
fun SearchScreen(viewModel: SearchViewModel) {
    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        topBar = {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
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
                        modifier = Modifier
                            .fillMaxSize()
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
        val uiModel by viewModel.uiModel.collectAsState()
        when (uiModel) {
            is SearchUiModel.None -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                ) {
                    NoMarvelItems(modifier = Modifier.align(Alignment.Center))
                }
            }

            is SearchUiModel.Success -> {
                val model = uiModel as SearchUiModel.Success
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                ) {
                    if (model.hasNoItem) {
                        NoMarvelItems(modifier = Modifier.align(Alignment.Center))
                    } else {
                        MarvelList(
                            marvelHeroes = model.marvelHeroes,
                            onItemClick = {},
                        )
                    }
                }
            }

            SearchUiModel.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = MarvelpediaTheme.colors.onBackground,
                    )
                }
            }
        }
    }
}

@Composable
fun MarvelList(
    marvelHeroes: List<MarvelHeroModel>,
    onItemClick: (MarvelHeroModel) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(8.dp),
    state: LazyGridState = rememberLazyGridState(),
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier,
        state = state,
        contentPadding = contentPadding,
    ) {
        items(
            items = marvelHeroes,
            key = { it.id },
            contentType = { "marvelHero" },
        ) { marvelHero ->
            MarvelItem(
                marvelHero = marvelHero,
                onClick = onItemClick,
                modifier = Modifier.padding(4.dp),
            )
        }
    }
}

@Composable
private fun MarvelItem(
    marvelHero: MarvelHeroModel,
    onClick: (MarvelHeroModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        color = MarvelpediaTheme.colors.onSurface.copy(alpha = 0.1f),
        shape = MarvelpediaTheme.shapes.medium,
        elevation = 0.dp,
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
