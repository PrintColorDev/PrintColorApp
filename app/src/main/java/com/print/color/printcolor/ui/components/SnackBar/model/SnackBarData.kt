package com.print.color.printcolor.ui.components.SnackBar.model

import com.print.color.printcolor.ui.components.SnackBar.model.SnackBarData.SnackBarType.SB_ERROR
import com.print.color.printcolor.ui.components.SnackBar.model.SnackBarData.SnackBarType.SB_INFO
import com.print.color.printcolor.ui.components.SnackBar.model.SnackBarData.SnackBarType.SB_SUCCESS

data class SnackBarData(val message: String, val type: SnackBarType) {

    enum class SnackBarType {
        SB_SUCCESS, SB_ERROR, SB_INFO, SB_ACTION
    }
}

/** Helper object to provide different variants of SnackBar.
 * The consumer should use this object to provide the desired variant.
 */
object SnackBarDefaultVariants {
    fun snackBarSuccess(
        message: String,
    ) = SnackBarData(message = message, type = SB_SUCCESS)

    fun snackBarError(
        message: String,
    ) = SnackBarData(message = message, type = SB_ERROR)

    fun snackBarInfo(
        message: String,
    ) = SnackBarData(message = message, type = SB_INFO)
}
