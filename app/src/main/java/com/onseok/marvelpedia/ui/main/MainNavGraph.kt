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
package com.onseok.marvelpedia.ui.main

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.onseok.marvelpedia.feature.search.MainScreen
import com.onseok.marvelpedia.feature.search.SearchViewModel

private enum class Screen(val route: String) {
    Main("main"),
    Detail("detail")
}

private fun NavController.navigateToDetail(marvelId: String) {
    navigate(route = Screen.Detail.route + "/" + marvelId)
}

@Composable
fun MainNavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController,
        startDestination = Screen.Main.route,
    ) {
        composable(Screen.Main.route) {
            val viewModel = hiltViewModel<SearchViewModel>()
            MainScreen(viewModel = viewModel)
        }
    }
}
