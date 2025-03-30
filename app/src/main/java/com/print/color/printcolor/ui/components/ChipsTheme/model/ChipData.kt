package com.print.color.printcolor.ui.components.ChipsTheme.model

import androidx.compose.ui.graphics.painter.Painter
import com.print.color.printcolor.ui.components.ChipsTheme.model.ChipData.ChipType

/**
 * @param text: The text of the chip.
 * @param isSelected: The state of the chip.
 * @param icon: The icon of the chip.
 * @param contentDescription: The content description of the chip.
 * */

data class ChipData(
    val text: String,
    val isSelected: Boolean?,
    val icon: Painter?,
    val contentDescription: String,
    val type: ChipType
) {
    enum class ChipType{
        ASSIST_CHIP,
        FILTER_CHIP,
        INPUT_CHIP,
        SUGGESTION_CHIP
    }
}

/**
 * Object that contains the default variants of the button.
 * */
object ChipsDefaultVariants {
    fun chipAssist(
        text: String,
        icon: Painter,
        contentDescription: String,
        type: ChipType
    ) = ChipData(
        text = text,
        isSelected = null,
        icon = icon,
        contentDescription = contentDescription,
        type = type
    )

    fun chipFilter(
        text: String,
        isSelected: Boolean,
        icon: Painter,
        contentDescription: String,
        type: ChipType
    ) = ChipData(
        text = text,
        isSelected = isSelected,
        icon = icon,
        contentDescription = contentDescription,
        type = type
    )

    fun chipInput(
        text: String,
        isSelected: Boolean,
        icon: Painter,
        contentDescription: String,
        type: ChipType
    ) = ChipData(
        text = text,
        isSelected = isSelected,
        icon = icon,
        contentDescription = contentDescription,
        type = type
    )

    fun chipSuggestion(
        text: String,
        isSelected: Boolean,
        contentDescription: String,
        type: ChipType
    ) = ChipData(
        text = text,
        isSelected = isSelected,
        icon = null,
        contentDescription = contentDescription,
        type = type
    )
}
