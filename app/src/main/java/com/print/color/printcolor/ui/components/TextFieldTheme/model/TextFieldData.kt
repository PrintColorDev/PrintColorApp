package com.print.color.printcolor.ui.components.TextFieldTheme.model

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType

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