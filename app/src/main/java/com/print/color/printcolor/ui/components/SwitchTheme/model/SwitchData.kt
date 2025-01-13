package com.print.color.printcolor.ui.components.SwitchTheme.model

/**
 * @param switchString: The string to be displayed on the switch.
 * @param isChecked: The state of the switch.
 * @param onCheckedChange: The callback to be invoked when the state of the switch changes.
 * */

data class SwitchData(
    val switchString: String,
    val isChecked: Boolean,
    val onCheckedChange: (Boolean) -> Unit
)
