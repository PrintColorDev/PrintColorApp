package com.print.color.printcolor.ui.components.AlertDialog.model

import androidx.annotation.RawRes
import com.print.color.printcolor.ui.components.AlertDialog.model.AlertDialogData.AlertDialogType

/**
 * @param title: The title of the alert dialog.
 * @param message: The message to be displayed in the alert dialog.
 * @param confirmButtonText: The text for the confirm button.
 * @param dismissButtonText: The text for the dismiss button.
 * @param dismissOnClickOutside: Whether the dialog should be dismissed when clicking outside of it.
 * @param onConfirm: The action to be performed when the confirm button is clicked.
 * @param onDismiss: The action to be performed when the dismiss button is clicked.
 * @param type: The type of the alert dialog.
 * */

data class AlertDialogData(
    val title: String,
    val message: String,
    val confirmButtonText: String,
    val dismissButtonText: String,
    val dismissOnClickOutside: Boolean,
    val onConfirm: () -> Unit,
    val onDismiss: () -> Unit,
    val type: AlertDialogType,
    @RawRes val lottieAnimation: Int?
) {
    /** Enum class to handle the different types of alert dialogs. */
    enum class AlertDialogType {
        CONFIRMATION,
        ANIMATION
    }
}

/**
 * Object that contains the default variants of the alertDialogs.
 * */
object AlertDialogDefaultVariants {

    fun alertDialogDefault (
        title: String,
        message: String,
        confirmButtonText: String,
        dismissButtonText: String,
        dismissOnClickOutside: Boolean,
        onConfirm: () -> Unit,
        onDismiss: () -> Unit,
        type: AlertDialogType
    ) = AlertDialogData(
        title = title,
        message = message,
        confirmButtonText = confirmButtonText,
        dismissButtonText = dismissButtonText,
        dismissOnClickOutside = dismissOnClickOutside,
        onConfirm = onConfirm,
        onDismiss = onDismiss,
        type = type,
        lottieAnimation = null
    )

    fun alertDialogAnimation (
        title: String,
        message: String,
        confirmButtonText: String,
        dismissButtonText: String,
        dismissOnClickOutside: Boolean,
        onConfirm: () -> Unit,
        onDismiss: () -> Unit,
        type: AlertDialogType,
        lottieAnimation: Int
    ) = AlertDialogData(
        title = title,
        message = message,
        confirmButtonText = confirmButtonText,
        dismissButtonText = dismissButtonText,
        dismissOnClickOutside = dismissOnClickOutside,
        onConfirm = onConfirm,
        onDismiss = onDismiss,
        type = type,
        lottieAnimation = lottieAnimation
    )
}


