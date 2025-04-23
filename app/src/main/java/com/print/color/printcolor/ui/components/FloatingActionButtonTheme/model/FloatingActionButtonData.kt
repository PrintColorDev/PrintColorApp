package com.print.color.printcolor.ui.components.FloatingActionButtonTheme.model

import androidx.compose.ui.graphics.painter.Painter
import com.print.color.printcolor.ui.components.FloatingActionButtonTheme.model.FloatingActionButtonData.TypeFAB

/**
 * @param icon: The leading icon of the FAB.
 * @param typeFAB: The type of the FAB.
 * @param contentDescription: The content description of the FAB.
 * */
data class FloatingActionButtonData(
    val icon: Painter?,
    val typeFAB: TypeFAB,
    val contentDescription: String
) {
    enum class TypeFAB {
        SMALL_FAB,
        NORMAL_FAB
    }
}

/**
 * Object that contains the default variants of the button.
 * */
object FloatingActionButtonDefaultVariants {
    fun smallFAB(
        icon: Painter,
        contentDescription: String,
        typeFAB: TypeFAB
    ) = FloatingActionButtonData(
        icon = icon,
        typeFAB = typeFAB,
        contentDescription = contentDescription)

    fun normalFAB(
        icon: Painter,
        contentDescription: String,
        typeFAB: TypeFAB
    ) = FloatingActionButtonData(
        icon = icon,
        typeFAB = typeFAB,
        contentDescription = contentDescription)
}