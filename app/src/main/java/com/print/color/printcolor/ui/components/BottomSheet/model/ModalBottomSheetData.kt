package com.print.color.printcolor.ui.components.BottomSheet.model

/**
 * @param title: The title of the alert dialog.
 * */
data class ModalBottomSheetData(val title: String)

/**
 * Object that contains the default variants of the button.
 * */
object ModalBottomSheetThemeDefaultVariants {
    fun modalBottomSheetData(title: String) = ModalBottomSheetData(title = title)
}