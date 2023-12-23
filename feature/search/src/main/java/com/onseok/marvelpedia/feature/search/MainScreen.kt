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

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.onseok.marvelpedia.core.designsystem.icon.MarvelpediaIcons
import com.onseok.marvelpedia.core.designsystem.theme.MarvelpediaTheme
import com.onseok.marvelpedia.core.resources.R
import com.onseok.marvelpedia.feature.favorite.FavoriteScreen

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun MainScreen(viewModel: SearchViewModel) {
    val currentMainTab by viewModel.selectedMainTab.collectAsState()
    val tabs = MainTabUiModel.entries.toTypedArray()
    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        bottomBar = {
            BottomNavigation {
                tabs.forEach { tab ->
                    val selected = currentMainTab == tab
                    BottomNavigationItem(
                        icon = {
                            when (tab) {
                                MainTabUiModel.Search -> {
                                    Icon(
                                        painter = rememberAnimatedVectorPainter(
                                            AnimatedImageVector.animatedVectorResource(
                                                MarvelpediaIcons.AvdSearchSelected,
                                            ),
                                            selected,
                                        ),
                                        contentDescription = null,
                                    )
                                }

                                MainTabUiModel.Favorite -> {
                                    Icon(
                                        painter = rememberAnimatedVectorPainter(
                                            AnimatedImageVector.animatedVectorResource(
                                                MarvelpediaIcons.AvdFavoriteSelected,
                                            ),
                                            selected,
                                        ),
                                        contentDescription = null,
                                    )
                                }
                            }
                        },
                        label = {
                            Text(
                                text = when (tab) {
                                    MainTabUiModel.Search -> stringResource(R.string.menu_search)
                                    MainTabUiModel.Favorite -> stringResource(R.string.menu_favorite)
                                },
                            )
                        },
                        selected = selected,
                        onClick = {
                            viewModel.onMainTabSelected(tab)
                        },
                        selectedContentColor = MarvelpediaTheme.colors.secondary,
                        unselectedContentColor = MarvelpediaTheme.colors.onSurface.copy(
                            alpha = ContentAlpha.disabled,
                        ),
                    )
                }
            }
        },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                when (currentMainTab) {
                    MainTabUiModel.Search -> {
                        SearchScreen(viewModel = viewModel)
                    }

                    MainTabUiModel.Favorite -> {
                        FavoriteScreen(viewModel = hiltViewModel())
                    }
                }
            }
        },
    )
}
