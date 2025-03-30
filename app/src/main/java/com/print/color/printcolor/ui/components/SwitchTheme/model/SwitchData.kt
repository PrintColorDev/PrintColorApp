package com.print.color.printcolor.ui.components.SwitchTheme.model

/**
 * @param switchString: The string to be displayed on the switch.
 * @param isChecked: The state of the switch.
 * @param onCheckedChange: The callback to be invoked when the state of the switch changes.
 * @param contentDescription: The content description of the switch.
 * */

data class SwitchData(
    val switchString: String,
    val isChecked: Boolean,
    val onCheckedChange: (Boolean) -> Unit,
    val contentDescription: String
)

/**
 * Object that contains the default variants of the Switch.
 * */
object SwitchDefaultVariants {
    fun switchDefault(
        switchString: String,
        isChecked: Boolean,
        onCheckedChange: (Boolean) -> Unit,
        contentDescription: String) = SwitchData(
            switchString = switchString,
            isChecked = isChecked,
            onCheckedChange = onCheckedChange,
            contentDescription = contentDescription
        )
}
