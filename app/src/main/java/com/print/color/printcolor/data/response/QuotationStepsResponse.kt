package com.print.color.printcolor.data.response

import android.util.Log
import com.print.color.printcolor.domain.model.QuotationStep
import com.print.color.printcolor.domain.model.QuotationSteps
import com.print.color.printcolor.R

data class QuotationStepsResponse(
    val id: String = "",
    val steps: Map<String, StepResponse> = emptyMap()
) {
    /*fun toDomain(): QuotationSteps {
        Log.d("QuotationStepsResponse", "Recibido desde Firestore: id=$id, steps=$steps")

        val mappedSteps = steps.map { (key, stepResponse) ->
            Log.d("QuotationStepsResponse", "Paso: key=$key, stepKey=${stepResponse.stepKey}, stepValue=${stepResponse.stepValue}")
            stepResponse.toDomain()
        }
        return QuotationSteps(id = id, steps = mappedSteps)
    }*/
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
