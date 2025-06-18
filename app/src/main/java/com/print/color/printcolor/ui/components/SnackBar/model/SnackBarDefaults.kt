package com.print.color.printcolor.ui.components.SnackBar.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.print.color.printcolor.ui.theme.PrintColorTheme

/** Helper Object to provide color scheme and typography for SnackBar.*/
object SnackBarDefaults {

    /**
     * Function to pass the different color to this component
     *
     * @return [SnackBarColors] instance for the component to read from.*/
    @Composable
    fun snackBarColors(
        backgroundSuccessColor: Color = PrintColorTheme.alerts.success,
        backgroundErrorColor: Color = PrintColorTheme.alerts.error,
        backgroundInfoColor: Color = PrintColorTheme.alerts.warning,
        textColor: Color = Color.Black,
        borderSuccessColor: Color = PrintColorTheme.alerts.successBorder,
        borderErrorColor: Color = PrintColorTheme.alerts.errorBorder,
        borderInfoColor: Color = PrintColorTheme.alerts.warningBorder
    ): SnackBarColors = SnackBarColors(
        backgroundSuccessColor = backgroundSuccessColor,
        backgroundErrorColor = backgroundErrorColor,
        backgroundInfoColor = backgroundInfoColor,
        textColor = textColor,
        borderSuccessColor = borderSuccessColor,
        borderErrorColor = borderErrorColor,
        borderInfoColor = borderInfoColor
    )
}

/** Model that contains the colors and variants of SnackBar.*/
@Immutable
class SnackBarColors internal constructor(
    val backgroundSuccessColor: Color,
    val backgroundErrorColor: Color,
    val backgroundInfoColor: Color,
    val textColor: Color,
    val borderSuccessColor: Color,
    val borderErrorColor: Color,
    val borderInfoColor: Color
)