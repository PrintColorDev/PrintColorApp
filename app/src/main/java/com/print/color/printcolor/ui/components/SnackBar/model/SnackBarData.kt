package com.print.color.printcolor.ui.components.SnackBar.model

import androidx.compose.ui.graphics.painter.Painter
import com.print.color.printcolor.ui.components.SnackBar.model.SnackBarData.SnackBarType.SB_ERROR
import com.print.color.printcolor.ui.components.SnackBar.model.SnackBarData.SnackBarType.SB_INFO
import com.print.color.printcolor.ui.components.SnackBar.model.SnackBarData.SnackBarType.SB_SUCCESS

data class SnackBarData(val message: String, val type: SnackBarType, val icon: Painter?) {

    enum class SnackBarType {
        SB_SUCCESS, SB_ERROR, SB_INFO, SB_ACTION
    }
}

object SnackBarDefaultVariants {
    fun snackBarSuccess(
        message: String,
        icon: Painter?
    ) = SnackBarData(message = message, type = SB_SUCCESS, icon = icon)

    fun snackBarError(
        message: String,
        icon: Painter?
    ) = SnackBarData(message = message, type = SB_ERROR, icon = icon)

    fun snackBarInfo(
        message: String,
        icon: Painter?
    ) = SnackBarData(message = message, type = SB_INFO, icon = icon)

}
