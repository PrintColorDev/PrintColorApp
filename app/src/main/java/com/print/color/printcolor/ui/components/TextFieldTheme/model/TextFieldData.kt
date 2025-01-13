package com.print.color.printcolor.ui.components.TextFieldTheme.model

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType

/**
 * @param textFieldType: The type of the text field.
 * @param label: The label of the text field.
 * @param placeHolder: The placeholder of the text field.
 * @param keyboardType: The type of keyboard to be used.
 * @param leadingIcon: The leading icon of the text field.
 * */

data class TextFieldData(
    val textFieldType: TextFieldType,
    val label: String,
    val placeHolder: String,
    val keyboardType: KeyboardType,
    val leadingIcon: ImageVector? = null
)

enum class TextFieldType {
    FILLED,
    OUTLINED
}