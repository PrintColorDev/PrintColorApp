package com.print.color.printcolor.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val lightScheme = lightColorScheme(
    primary = Pink,
    onPrimary = White,
    primaryContainer = LightPink,
    onPrimaryContainer = Black,
    secondary = LightBlue,
    onSecondary = White,
    secondaryContainer = LightLightBlue,
    onSecondaryContainer = Black,
    tertiary = Yellow,
    onTertiary = Black,
    tertiaryContainer = LightYellow,
    onTertiaryContainer = Black,
    background = White,
    onBackground = Black,
    surface = White,
    onSurface = Black,
    surfaceVariant = LightGray,
    onSurfaceVariant = Black,
    outline = DarkGray,
    inverseSurface = Black,
    inverseOnSurface = White,
    inversePrimary = DarkPink,
    surfaceTint = Green,
)

val darkScheme = darkColorScheme(
    primary = Pink,
    onPrimary = Black,
    primaryContainer = DarkPink,
    onPrimaryContainer = White,
    secondary = LightBlue,
    onSecondary = Black,
    secondaryContainer = DarkLightBlue,
    onSecondaryContainer = White,
    tertiary = Yellow,
    onTertiary = Black,
    tertiaryContainer = DarkYellow,
    onTertiaryContainer = White,
    background = Black,
    onBackground = White,
    surface = Black,
    onSurface = White,
    surfaceVariant = DarkGray,
    onSurfaceVariant = White,
    outline = LightGray,
    inverseSurface = White,
    inverseOnSurface = Black,
    inversePrimary = LightPink,
    surfaceTint = Green
)

//val PrintColorExtraColor

@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)


data class AlertColors(
    val  success: Color,
    val successBorder: Color,
    val onSuccess: Color,
    val warning: Color,
    val warningBorder: Color,
    val onWarning: Color,
    val error: Color,
    val errorBorder: Color,
    val onError: Color
)

private val LocalAlertColors = staticCompositionLocalOf {
    AlertColors(
        success = Color.Unspecified,
        successBorder = Color.Unspecified,
        onSuccess = Color.Unspecified,
        warning = Color.Unspecified,
        warningBorder = Color.Unspecified,
        onWarning = Color.Unspecified,
        error = Color.Unspecified,
        errorBorder = Color.Unspecified,
        onError = Color.Unspecified
    )
}

@Composable
fun PrintColorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Set default to false
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        darkScheme
    } else {
        lightScheme
    }

    val alertColors = if (darkTheme) {
        AlertColors(
            success = Success, // Assuming Success color works for dark theme
            successBorder = SuccessBorder,
            onSuccess = Black, // Or White, depending on your Success color's brightness
            warning = Warning, // Assuming Warning color works for dark theme
            warningBorder = WarningBorder,
            onWarning = Black, // Or White, depending on your Warning color's brightness
            error = Error, // Your custom error from Color.kt
            errorBorder = ErrorBorder,
            onError = White    // Ensure contrast
        )
    } else {
        AlertColors(
            success = Success,
            successBorder = SuccessBorder,
            onSuccess = White, // Or Black
            warning = Warning,
            warningBorder = WarningBorder,
            onWarning = Black, // Or White
            error = Error, // Your custom error from Color.kt
            errorBorder = ErrorBorder,
            onError = White   // Ensure contrast
        )
    }

    CompositionLocalProvider(LocalAlertColors provides alertColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            //typography = Typography, // Ensure Typography is defined
            content = content
        )
    }
}

object PrintColorTheme {
    val colorScheme: androidx.compose.material3.ColorScheme
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.colorScheme

    val typography: androidx.compose.material3.Typography
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.typography

    val alerts: AlertColors
        @Composable
        @ReadOnlyComposable
        get() = LocalAlertColors.current
}

/*@Composable
fun PrintColorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
*
     * Dynamic Colors are commented to force light or dark theme
     *

val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }


    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}*/
