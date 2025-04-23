package com.print.color.printcolor.ui.components.IconClickButton.model

import androidx.compose.ui.graphics.painter.Painter

/**
 * @param icon: The icon to be displayed in the button.
 * @param contentDescription: The content description of the icon.
 * */
data class IconClickButtonData(
    val icon: Painter?,
    val contentDescription: String,
)

/**
 * Object that contains the default variants of the IconClickButton.
 * */
object IconClickButtonDefaultVariants {
    fun iconClickButtonDefault(
        icon: Painter,
        contentDescription: String,
    ) = IconClickButtonData(
        icon = icon,
        contentDescription = contentDescription,
    )
}
