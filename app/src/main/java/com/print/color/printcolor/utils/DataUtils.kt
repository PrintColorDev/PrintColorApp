package com.print.color.printcolor.utils

import com.print.color.printcolor.R
import java.util.Date

/** Generate a unique ID based on the current timestamp */
fun generateUniqueId(): String {
    return Date().time.toString()
}

fun getIconForStep(stepKey: String): Int {
    return when (stepKey) {
        CONST_QUOTATION_STEP_KEY1 -> R.drawable.ic_pcs_notes
        CONST_QUOTATION_STEP_KEY2 -> R.drawable.ic_pcs_success
        CONST_QUOTATION_STEP_KEY3 -> R.drawable.ic_pcs_design
        CONST_QUOTATION_STEP_KEY4 -> R.drawable.ic_pcs_print
        CONST_QUOTATION_STEP_KEY5 -> R.drawable.ic_pcs_delivery
        CONST_QUOTATION_STEP_KEY6 -> R.drawable.ic_pcs_survey
        else -> R.drawable.ic_pcs_client
    }
}