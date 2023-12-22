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
package com.onseok.marvelpedia.core.designsystem.theme

import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Stable
data class MarvelpediaColors(
    internal val material: Colors,
    val divider: Color,
    val star: Color,
    val dim: Color,
) {
    val primary: Color get() = material.primary
    val primaryVariant: Color get() = material.primaryVariant
    val secondary: Color get() = material.secondary
    val secondaryVariant: Color get() = material.secondaryVariant
    val background: Color get() = material.background
    val surface: Color get() = material.surface
    val error: Color get() = material.error
    val onPrimary: Color get() = material.onPrimary
    val onSecondary: Color get() = material.onSecondary
    val onBackground: Color get() = material.onBackground
    val onSurface: Color get() = material.onSurface
    val onError: Color get() = material.onError
    val isLight: Boolean get() = material.isLight

    val primarySurface: Color get() = material.primarySurface
}

fun lightMarvelpediaColors(
    material: Colors = lightColors(),
    divider: Color = Color(0xFFF5F5F5),
    star: Color = Color(0xFFFFC107),
    dim: Color = Color(0xDDFFFFFF),
): MarvelpediaColors = MarvelpediaColors(
    material = material,
    divider = divider,
    star = star,
    dim = dim,
)

fun darkMarvelpediaColors(
    material: Colors = darkColors(),
    divider: Color = Color(0xFF212121),
    star: Color = Color(0xFFFFC107),
    dim: Color = Color(0xAA000000),
): MarvelpediaColors = MarvelpediaColors(
    material = material,
    divider = divider,
    star = star,
    dim = dim,
)

internal val LocalMarvelpediaColors = staticCompositionLocalOf { lightMarvelpediaColors() }
