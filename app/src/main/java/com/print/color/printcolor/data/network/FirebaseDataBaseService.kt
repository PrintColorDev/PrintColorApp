package com.print.color.printcolor.data.network

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.Date
import javax.inject.Inject
import kotlin.coroutines.resume

class FirebaseDataBaseService @Inject constructor(private val firebaseFireStore: FirebaseFirestore) {

    companion object {
        const val QUOTATION_PATH = "quotations"
    }

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
        val id = generateProductId()
        val quotation = hashMapOf(
            "id" to id,
            "clientName" to clientName,
            "customerName" to customerName,
            "contact" to contact,
            "extraData" to extraData,
            "isBillRequired" to isBillRequired,
            "taxRegime" to taxRegime,
            "rfc" to rfc,
            "address" to address,
            "zipCode" to zipCode,
            "state" to state,
            "municipality" to municipality,
            "cfdi" to cfdi,
            "email" to email,
            "paymentMethod" to paymentMethod
        )

        return suspendCancellableCoroutine { suspendCancellableCoroutine ->
            firebaseFireStore.collection(QUOTATION_PATH).document(id).set(quotation)
                .addOnSuccessListener {
                    suspendCancellableCoroutine.resume(true)
                }.addOnFailureListener {
                    suspendCancellableCoroutine.resume(false)
            }
        }
    }

    private fun generateProductId(): String {
        return Date().time.toString()
    }
}