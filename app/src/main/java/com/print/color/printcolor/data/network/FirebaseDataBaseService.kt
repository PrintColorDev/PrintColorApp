package com.print.color.printcolor.data.network

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.print.color.printcolor.data.response.QuotationResponse
import com.print.color.printcolor.data.response.QuotationStepsResponse
import com.print.color.printcolor.domain.model.Quotation
import com.print.color.printcolor.domain.model.QuotationStep
import com.print.color.printcolor.domain.model.QuotationSteps
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.coroutines.resume
import com.print.color.printcolor.R
import com.print.color.printcolor.utils.CONST_QUOTATION_STEP1_ID
import com.print.color.printcolor.utils.CONST_QUOTATION_STEP2_ID
import com.print.color.printcolor.utils.CONST_QUOTATION_STEP3_ID
import com.print.color.printcolor.utils.CONST_QUOTATION_STEP4_ID
import com.print.color.printcolor.utils.CONST_QUOTATION_STEP5_ID
import com.print.color.printcolor.utils.CONST_QUOTATION_STEP6_ID
import com.print.color.printcolor.utils.CONST_QUOTATION_STEP_KEY
import com.print.color.printcolor.utils.CONST_QUOTATION_STEP_KEY1
import com.print.color.printcolor.utils.CONST_QUOTATION_STEP_KEY2
import com.print.color.printcolor.utils.CONST_QUOTATION_STEP_KEY3
import com.print.color.printcolor.utils.CONST_QUOTATION_STEP_KEY4
import com.print.color.printcolor.utils.CONST_QUOTATION_STEP_KEY5
import com.print.color.printcolor.utils.CONST_QUOTATION_STEP_KEY6
import com.print.color.printcolor.utils.CONST_QUOTATION_STEP_QUOTATION_ICON
import com.print.color.printcolor.utils.CONST_QUOTATION_STEP_QUOTATION_ID
import com.print.color.printcolor.utils.CONST_QUOTATION_STEP_QUOTATION_STEPS_MAPS
import com.print.color.printcolor.utils.CONST_QUOTATION_STEP_VALUE
import com.print.color.printcolor.utils.generateUniqueId


class FirebaseDataBaseService @Inject constructor(private val firebaseFireStore: FirebaseFirestore) {

    /** region Firebase path's*/
    companion object {
        const val QUOTATION_PATH = "quotations"
        const val QUOTATION_STEPS_PATH = "quotationSteps"
    }
    /** endregion Firebase path's*/

    /** Insert New Quotation */
    suspend fun newQuotation(
        clientName: String,
        customerName: String,
        contact: String,
        extraData: String,
        isBillRequired: Boolean,
        taxRegime: String,
        rfc: String,
        address: String,
        zipCode: String,
        state: String,
        municipality: String,
        cfdi: String,
        email: String,
        paymentMethod: String
    ): Boolean {
        val id = generateUniqueId()
        val quotationStepsId = generateUniqueId()

        /** Generate new steps */
        val newSteps = listOf(
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

        val stepsSaved = newQuotationSteps(quotationStepsId, newSteps)
        if (!stepsSaved) {
            Log.d("Firestore", "Error al crear los pasos de cotización")
            return false
        }

        val quotation = hashMapOf(
            "id" to id,
            "clientName" to clientName,
            "customerName" to customerName,
            "contact" to contact,
            "extraData" to extraData,
            "billRequired" to isBillRequired,
            "taxRegime" to taxRegime,
            "rfc" to rfc,
            "address" to address,
            "zipCode" to zipCode,
            "state" to state,
            "municipality" to municipality,
            "cfdi" to cfdi,
            "email" to email,
            "paymentMethod" to paymentMethod,
            "quotationStepsId" to quotationStepsId
        )

        return suspendCancellableCoroutine { continuation ->
            firebaseFireStore.collection(QUOTATION_PATH)
                .document(id)
                .set(quotation)
                .addOnSuccessListener {
                    continuation.resume(true)
                }.addOnFailureListener {
                    Log.e("Firestore", "Error al crear la cotización", it)
                    continuation.resume(false)
                }
        }
    }

    /** Insert New Quotation Steps */
    suspend fun newQuotationSteps(quotationStepsId: String, steps: List<QuotationStep>): Boolean {
        val stepsMap = steps.associate { step ->
            step.id to hashMapOf(
                CONST_QUOTATION_STEP_KEY to step.stepKey,
                CONST_QUOTATION_STEP_VALUE to step.stepValue,
                CONST_QUOTATION_STEP_QUOTATION_ICON to step.quotationIcon
            )
        }

        val quotationSteps = hashMapOf(
            CONST_QUOTATION_STEP_QUOTATION_ID to quotationStepsId,
            CONST_QUOTATION_STEP_QUOTATION_STEPS_MAPS to stepsMap
        )

        return suspendCancellableCoroutine { continuation ->
            firebaseFireStore.collection(QUOTATION_STEPS_PATH)
                .document(quotationStepsId)
                .set(quotationSteps)
                .addOnSuccessListener {
                    continuation.resume(true)
                }.addOnFailureListener {
                    Log.e("Firestore", "Error al guardar los pasos de cotización", it)
                    continuation.resume(false)
                }
        }
    }

    /** Update Step */
    suspend fun updateStep(quotationStepId: String?, stepKey: String, value: Boolean) {
        try {
            firebaseFireStore.collection("quotationSteps")
                .document(quotationStepId.toString())
                .update(stepKey, value)
                .await()
            Log.d("Firebase", "Step $stepKey actualizado a $value")
        } catch (e: Exception) {
            Log.e("Firebase", "Error al actualizar el Step", e)
        }
    }

    /** Get all Quotation */
    suspend fun getAllProducts(): List<Quotation> {
        return firebaseFireStore.collection(QUOTATION_PATH).get().await().map { quotation ->
            quotation.toObject(QuotationResponse::class.java).toDomain()
        }
    }

    /** Get Quotation Steps */
    suspend fun getQuotationSteps(quotationStepsId: String): QuotationSteps? {
        return try {
            val document = firebaseFireStore.collection(QUOTATION_STEPS_PATH)
                .document(quotationStepsId)
                .get()
                .await()
            if (document.exists()) {
                document.toObject(QuotationStepsResponse::class.java)?.toDomain()
            } else {
                null
            }
        } catch (e: Exception) {
            Log.e("getQuotationStepsFailed", "Error obteniendo pasos de cotización", e)
            null
        }
    }
}