package com.print.color.printcolor.ui.components.TextFieldTheme.model

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.input.KeyboardType

/**
 * @param textFieldType: The type of the text field.
 * @param label: The label of the text field.
 * @param placeHolder: The placeholder of the text field.
 * @param keyboardType: The type of keyboard to be used.
 * @param leadingIcon: The leading icon of the text field.
 * @param isTextCountRequired: Whether the text count is required.
 * */

data class TextFieldData(
    val textFieldType: TextFieldType,
    val label: String,
    val placeHolder: String,
    val keyboardType: KeyboardType,
    val leadingIcon: Painter?,
    val isTextCountRequired: Boolean = false
) {
    enum class TextFieldType {
        FILLED,
        OUTLINED,
        OUTLINED_PASSWORD,
        OUTLINED_LIST
    }
}

/**
 * Object that contains the default variants of the Switch.
 * */
object TextFieldDefaultVariants {
    fun textFieldFilled(
        label: String,
        placeHolder: String,
        keyboardType: KeyboardType,
        leadingIcon: Painter?,
        isTextCountRequired: Boolean = false
    ) = TextFieldData(
        textFieldType = TextFieldData.TextFieldType.FILLED,
        label = label,
        placeHolder = placeHolder,
        keyboardType = keyboardType,
        leadingIcon = leadingIcon,
        isTextCountRequired = isTextCountRequired
    )
    fun textFieldOutlined(
        label: String,
        placeHolder: String,
        keyboardType: KeyboardType,
        leadingIcon: Painter?,
        isTextCountRequired: Boolean = false
    ) = TextFieldData(
        textFieldType = TextFieldData.TextFieldType.OUTLINED,
        label = label,
        placeHolder = placeHolder,
        keyboardType = keyboardType,
        leadingIcon = leadingIcon,
        isTextCountRequired = isTextCountRequired,
    )
    fun textFieldOutlinedList(
        label: String,
        placeHolder: String,
        keyboardType: KeyboardType,
        leadingIcon: Painter?,
        isTextCountRequired: Boolean = false
    ) = TextFieldData(
        textFieldType = TextFieldData.TextFieldType.OUTLINED_LIST,
        label = label,
        placeHolder = placeHolder,
        keyboardType = keyboardType,
        leadingIcon = leadingIcon,
        isTextCountRequired = isTextCountRequired
    )
    fun textFieldOutlinedPassword(
        label: String,
        placeHolder: String,
        keyboardType: KeyboardType,
        leadingIcon: Painter?,
        isTextCountRequired: Boolean = false
    ) = TextFieldData(
        textFieldType = TextFieldData.TextFieldType.OUTLINED_PASSWORD,
        label = label,
        placeHolder = placeHolder,
        keyboardType = keyboardType,
        leadingIcon = leadingIcon,
        isTextCountRequired = isTextCountRequired,
    )
}

