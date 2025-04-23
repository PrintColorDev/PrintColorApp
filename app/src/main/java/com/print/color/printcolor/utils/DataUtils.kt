package com.print.color.printcolor.utils

import android.content.Context
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
        CONST_QUOTATION_STEP_KEY2 -> R.drawable.ic_pcs_confirm
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
        quotationIcon = R.drawable.ic_pcs_confirm
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

/** Fun to handle the step description. */
fun getCorrectStepDescriptionByStepKey(context: Context, stepKey: String) = when (stepKey) {
    CONST_QUOTATION_STEP_KEY1 -> getStringResource(
        context = context,
        stringResId = R.string.quotation_list_screen_tooltip_description_step1
    )

    CONST_QUOTATION_STEP_KEY2 -> getStringResource(
        context = context,
        stringResId = R.string.quotation_list_screen_tooltip_description_step2
    )

    CONST_QUOTATION_STEP_KEY3 -> getStringResource(
        context = context,
        stringResId = R.string.quotation_list_screen_tooltip_description_step3
    )

    CONST_QUOTATION_STEP_KEY4 -> getStringResource(
        context = context,
        stringResId = R.string.quotation_list_screen_tooltip_description_step4
    )

    CONST_QUOTATION_STEP_KEY5 -> getStringResource(
        context = context,
        stringResId = R.string.quotation_list_screen_tooltip_description_step5
    )

    CONST_QUOTATION_STEP_KEY6 -> getStringResource(
        context = context,
        stringResId = R.string.quotation_list_screen_tooltip_description_step6
    )

    else -> stepKey
}