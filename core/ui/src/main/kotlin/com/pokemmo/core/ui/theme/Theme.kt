package com.pokemmo.core.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary            = PokeMMOColors.AccentRed,
    onPrimary          = Color.White,
    primaryContainer   = Color(0xFF93000A),
    secondary          = PokeMMOColors.AccentGold,
    onSecondary        = Color.Black,
    background         = PokeMMOColors.DarkBackground,
    onBackground       = PokeMMOColors.OnDark,
    surface            = PokeMMOColors.DarkSurface,
    surfaceVariant     = PokeMMOColors.DarkSurfaceVar,
    onSurface          = PokeMMOColors.OnDark,
    onSurfaceVariant   = PokeMMOColors.OnDarkDim,
    outline            = Color(0xFF444444),
    outlineVariant     = Color(0xFF333333),
    inverseSurface     = Color(0xFFE0E0E0),
    inverseOnSurface   = Color(0xFF303030),
)

@Composable
fun PokeMMOTeamBuilderTheme(
    content: @Composable () -> Unit,
) {
    val colorScheme = DarkColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window ?: return@SideEffect
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = PokeMMOTypography,
        shapes = PokeMMOShapes,
        content = content,
    )
}
