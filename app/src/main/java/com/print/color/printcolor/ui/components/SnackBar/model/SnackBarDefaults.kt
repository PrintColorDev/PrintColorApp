package com.print.color.printcolor.ui.components.SnackBar.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.print.color.printcolor.ui.theme.PrintColorTheme

object SnackBarDefaults {

    @Composable
    fun snackBarColors(
        backgroundSuccessColor: Color = PrintColorTheme.alerts.success,
        backgroundErrorColor: Color = PrintColorTheme.alerts.error,
        backgroundInfoColor: Color = PrintColorTheme.alerts.warning,
        textColor: Color = Color.Black,
        iconColor: Color = Color.Black,
        borderSuccessColor: Color = PrintColorTheme.alerts.successBorder,
        borderErrorColor: Color = PrintColorTheme.alerts.errorBorder,
        borderInfoColor: Color = PrintColorTheme.alerts.warningBorder
    ): SnackBarColors = SnackBarColors(
        backgroundSuccessColor = backgroundSuccessColor,
        backgroundErrorColor = backgroundErrorColor,
        backgroundInfoColor = backgroundInfoColor,
        textColor = textColor,
        iconColor = iconColor,
        borderSuccessColor = borderSuccessColor,
        borderErrorColor = borderErrorColor,
        borderInfoColor = borderInfoColor
    )
}

@Immutable
class SnackBarColors internal constructor(
    val backgroundSuccessColor: Color,
    val backgroundErrorColor: Color,
    val backgroundInfoColor: Color,
    val textColor: Color,
    val iconColor: Color,
    val borderSuccessColor: Color,
    val borderErrorColor: Color,
    val borderInfoColor: Color
)