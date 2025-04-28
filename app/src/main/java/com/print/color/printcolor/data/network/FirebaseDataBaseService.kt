package com.print.color.printcolor.data.network

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.print.color.printcolor.data.response.QuotationResponse
import com.print.color.printcolor.data.response.QuotationStepsResponse
import com.print.color.printcolor.domain.model.Quotation
import com.print.color.printcolor.domain.model.QuotationStep
import com.print.color.printcolor.domain.model.QuotationSteps
import com.print.color.printcolor.utils.CONST_QUOTATION_STEP_KEY
import com.print.color.printcolor.utils.CONST_QUOTATION_STEP_QUOTATION_ICON
import com.print.color.printcolor.utils.CONST_QUOTATION_STEP_QUOTATION_ID
import com.print.color.printcolor.utils.CONST_QUOTATION_STEP_QUOTATION_STEPS_MAPS
import com.print.color.printcolor.utils.CONST_QUOTATION_STEP_VALUE
import com.print.color.printcolor.utils.generateUniqueId
import com.print.color.printcolor.utils.getQuotationStepList
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.coroutines.resume


class FirebaseDataBaseService @Inject constructor(private val firebaseFireStore: FirebaseFirestore) {

    /** region Firebase path's*/
    companion object {
        const val QUOTATION_PATH = "quotations"
        const val QUOTATION_STEPS_PATH = "quotationSteps"
        const val NEW_USER = "users"
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
        paymentMethod: String,
        currentStep: String,
        deleted: Boolean
    ): Boolean {
        val id = generateUniqueId()
        val quotationStepsId = generateUniqueId()

        val stepsSaved = newQuotationSteps(quotationStepsId, getQuotationStepList())
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
            "quotationStepsId" to quotationStepsId,
            "currentStep" to currentStep,
            "deleted" to deleted
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
            val updatePath = "steps.$stepKey.stepValue"

            firebaseFireStore.collection(QUOTATION_STEPS_PATH)
                .document(quotationStepId.toString())
                .update(updatePath, value)
                .await()
            Log.d("Firebase", "Step $stepKey actualizado a $value")
        } catch (e: Exception) {
            Log.e("Firebase", "Error al actualizar el Step", e)
        }
    }

    /** Fun to update current step */
    suspend fun updateCurrentStep(quotationId: String, currentStep: String) {
        try {
            val correctStepKey = when (currentStep) {
                "step1" -> "step_one"
                "step2" -> "step_two"
                "step3" -> "step_three"
                "step4" -> "step_four"
                "step5" -> "step_five"
                "step6" -> "step_six"
                else -> currentStep
            }// TODO update this logic in a utils fun
            firebaseFireStore.collection(QUOTATION_PATH)
                .document(quotationId)
                .update("currentStep", correctStepKey)
                .await()
            Log.d("FirebaseUpdateStep", "Step $correctStepKey actualizado a $correctStepKey")
        } catch (e: Exception) {
            Log.e("FirebaseUpdateStep", "Error al actualizar el Step", e)
        }
    }

    /** Get all Quotation */
    fun getAllQuotations(): Flow<List<Quotation>> = callbackFlow {
        val listener = firebaseFireStore.collection(QUOTATION_PATH)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }

                if (snapshot != null) {
                    val quotations = snapshot.documents.mapNotNull { document ->
                        document.toObject(QuotationResponse::class.java)?.toDomain()
                    }
                    trySend(quotations).isSuccess
                }
            }

        awaitClose { listener.remove() }
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

    /** Fun to delete Quotation */
    suspend fun deleteQuotation(quotationId: String) {
        try {
            firebaseFireStore.collection(QUOTATION_PATH)
                .document(quotationId)
                .update("deleted", true)
                .await()
        } catch (e: Exception) {
            Log.e("deleteQuotationFailed", "Error al eliminar la cotización", e)
        }
    }

    /** region SignUp functions*/

    suspend fun createNewUser() {

    }
    /** endregion SignUp functions*/
}

/** region filter chip functions. */
