package com.print.color.printcolor.utils

import com.print.color.printcolor.R
import com.print.color.printcolor.domain.model.QuotationStep
import java.util.Date

/** Fun to Generate a unique ID based on the current timestamp */
fun generateUniqueId(): String {
    return Date().time.toString()
}

/** Fun to get the icon for each step */
fun getIconForStep(stepKey: String): Int {
    return when (stepKey) {
        CONST_QUOTATION_STEP_KEY1 -> R.drawable.ic_pcs_notes
        CONST_QUOTATION_STEP_KEY2 -> R.drawable.ic_pcs_success
        CONST_QUOTATION_STEP_KEY3 -> R.drawable.ic_pcs_design
        CONST_QUOTATION_STEP_KEY4 -> R.drawable.ic_pcs_print
        CONST_QUOTATION_STEP_KEY5 -> R.drawable.ic_pcs_delivery
        CONST_QUOTATION_STEP_KEY6 -> R.drawable.ic_pcs_survey
        else -> R.drawable.ic_pc_logo
    }
}

/** Fun to get the quotation default step list */
fun getQuotationStepList(): List<QuotationStep> = listOf(
    QuotationStep(
        id = CONST_QUOTATION_STEP1_ID,
        stepKey = CONST_QUOTATION_STEP_KEY1,
        stepValue = false,
        quotationIcon = R.drawable.ic_pcs_notes
    ),
    QuotationStep(
        id = CONST_QUOTATION_STEP2_ID,
        stepKey = CONST_QUOTATION_STEP_KEY2,
        stepValue = false,
        quotationIcon = R.drawable.ic_pcs_success
    ),
    QuotationStep(
        id = CONST_QUOTATION_STEP3_ID,
        stepKey = CONST_QUOTATION_STEP_KEY3,
        stepValue = false,
        quotationIcon = R.drawable.ic_pcs_design
    ),
    QuotationStep(
        id = CONST_QUOTATION_STEP4_ID,
        stepKey = CONST_QUOTATION_STEP_KEY4,
        stepValue = false,
        quotationIcon = R.drawable.ic_pcs_print
    ),
    QuotationStep(
        id = CONST_QUOTATION_STEP5_ID,
        stepKey = CONST_QUOTATION_STEP_KEY5,
        stepValue = false,
        quotationIcon = R.drawable.ic_pcs_delivery
    ),
    QuotationStep(
        id = CONST_QUOTATION_STEP6_ID,
        stepKey = CONST_QUOTATION_STEP_KEY6,
        stepValue = false,
        quotationIcon = R.drawable.ic_pcs_survey
    )
)