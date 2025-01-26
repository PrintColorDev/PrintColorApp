package com.print.color.printcolor.ui.components.AlertDialog.model

data class AlertDialogData(
    val title: String,
    val message: String,
    val confirmButtonText: String,
    val dismissButtonText: String,
    val dismissOnClickOutside: Boolean,
    val onConfirm: () -> Unit,
    val onDismiss: () -> Unit,
    val type: AlertDialogType
)

enum class AlertDialogType {
    CONFIRMATION,
    ANIMATION
}
