package com.print.color.printcolor.data.response

import com.print.color.printcolor.domain.model.QuotationStep
import com.print.color.printcolor.domain.model.QuotationSteps
import com.print.color.printcolor.R

data class QuotationStepsResponse(
    val id: String = "",
    val steps: Map<String, StepResponse> = emptyMap()
) {
    val stepOrder = listOf("step_one", "step_two", "step_three", "step_four", "step_five", "step_six")

    fun toDomain(): QuotationSteps {
        val mappedSteps = steps.map { (_, stepResponse) ->
            stepResponse.toDomain()
        }.sortedBy { stepOrder.indexOf(it.stepKey) }

        return QuotationSteps(id = id, steps = mappedSteps)
    }


}

data class StepResponse(
    val stepKey: String = "",
    val stepValue: Boolean = false,
    val quotationIcon: Int = R.drawable.ic_pcs_client
) {
    fun toDomain(): QuotationStep {
        return QuotationStep(
            id = stepKey,
            stepKey = stepKey,
            stepValue = stepValue,
            quotationIcon = quotationIcon
        )
    }
}
