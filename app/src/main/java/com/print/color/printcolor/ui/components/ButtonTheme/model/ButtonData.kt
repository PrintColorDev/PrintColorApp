package com.print.color.printcolor.ui.components.ButtonTheme.model

import androidx.compose.ui.graphics.painter.Painter
import com.print.color.printcolor.ui.components.ButtonTheme.model.ButtonData.ButtonType

/**
 * @param label: The label of the button.
 * @param contentDescription: The content description of the button.
 * @param type: The type of the button.
 * @param isEnabled: The state of the button.
 * @param icon: The icon of the button.*/

data class ButtonData(
    val label: String,
    val contentDescription: String,
    val type: ButtonType,
    val isEnabled: Boolean = true,
    val icon: Painter?
) {
    enum class ButtonType {
        ELEVATED,
        OUTLINED,
        TEXT,
        FILLED,
        TONAL
    }
}

/**
 * Object that contains the default variants of the button.
 * */
object ButtonThemeDefaultVariants {

    fun buttonDefaultData(
        label: String,
        contentDescription: String,
        type: ButtonType,
        isEnabled: Boolean = true,
    ) = ButtonData(
        label = label,
        contentDescription = contentDescription,
        type = type,
        isEnabled = isEnabled,
        icon = null
    )

    fun buttonDataWithIcon(
        label: String,
        contentDescription: String,
        type: ButtonType,
        isEnabled: Boolean = true,
        icon: Painter
    ) = ButtonData(
        label = label,
        contentDescription = contentDescription,
        type = type,
        isEnabled = isEnabled,
        icon = icon
    )
}

