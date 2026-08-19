package br.com.ajudafio.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = AppPallet.PrimaryColor,
    onPrimary = Color.White,
    primaryContainer = AppPallet.PrimaryColorLight,
    onPrimaryContainer = Color.White,
    secondary = AppPallet.SecondaryColor,
    onSecondary = Color.White,
    secondaryContainer = AppPallet.SecondaryColorLight,
    onSecondaryContainer = Color.White,
    tertiary = AppPallet.AccentColor,
    onTertiary = Color.White,
    error = AppPallet.AccentColor,
    onError = Color.White,
    background = AppPallet.BackgroundColor,
    onBackground = AppPallet.TextColor,
    surface = AppPallet.SurfaceColor,
    onSurface = AppPallet.TextColor,
    surfaceVariant = AppPallet.BackgroundColor,
    onSurfaceVariant = AppPallet.TextColorSecondary,
    outline = AppPallet.TextColorSecondary,
)

private val DarkColorScheme = darkColorScheme(
    primary = AppPallet.PrimaryColorLight,
    onPrimary = Color(0xFF00234A),
    primaryContainer = AppPallet.PrimaryColorDark,
    onPrimaryContainer = Color.White,
    secondary = AppPallet.SecondaryColorLight,
    onSecondary = Color(0xFF0B2E08),
    secondaryContainer = AppPallet.SecondaryColorDark,
    onSecondaryContainer = Color.White,
    tertiary = AppPallet.AccentColor,
    onTertiary = Color.White,
    error = AppPallet.AccentColor,
    onError = Color.White,
    background = Color(0xFF121417),
    onBackground = Color(0xFFE7E9EA),
    surface = Color(0xFF1A1D1F),
    onSurface = Color(0xFFE7E9EA),
    surfaceVariant = Color(0xFF2A2D30),
    onSurfaceVariant = Color(0xFF9CA3AF),
    outline = Color(0xFF6B7280),
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    //val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = AppTypography,
        shapes = AppShapes,
        content = content,
    )
}
