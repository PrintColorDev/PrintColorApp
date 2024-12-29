package com.print.color.printcolor.ui.components.FloatingActionButtonTheme.model

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * @param stringFAB: The string to be displayed on the FAB.
 * @param leadingIcon: The leading icon of the FAB.
 * @param typeFAB: The type of the FAB.
 * */
data class FloatingActionButtonData(
    val stringFAB: String,
    val leadingIcon: ImageVector? = null,
    val typeFAB: TypeFAB
)

enum class TypeFAB {
    SMALL_FAB,
    ANIMATED_FAB
}