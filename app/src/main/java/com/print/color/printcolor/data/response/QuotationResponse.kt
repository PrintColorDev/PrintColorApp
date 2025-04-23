package com.print.color.printcolor.data.response

import android.util.Log
import com.google.firebase.database.PropertyName
import com.print.color.printcolor.domain.model.Quotation

data class QuotationResponse(
    val id: String = "",
    val clientName: String  = "",
    val customerName: String = "",
    val contact: String = "",
    val extraData: String = "",
    @get:PropertyName("isBillRequired")
    @set:PropertyName("isBillRequired")
    var isBillRequired: Boolean = false,
    val taxRegime: String = "",
    val rfc: String = "",
    val address: String = "",
    val zipCode: String = "",
    val state: String = "",
    val municipality: String = "",
    val cfdi: String = "",
    val email: String = "",
    val paymentMethod: String = "",
    val status: String = "",
    val quotationStepsId: String = "",
    val currentStep: String = "",
    var deleted: Boolean? = null
) {
    fun toDomain(): Quotation {
        Log.d("quotationStepsId", quotationStepsId.toString())
        Log.d("isDeleted", deleted.toString())
        return Quotation(
            id = id,
            clientName = clientName,
            customerName = customerName,
            contact = contact,
            extraData = extraData,
            isBillRequired = isBillRequired,
            taxRegime = taxRegime,
            rfc = rfc,
            address = address,
            zipCode = zipCode,
            state = state,
            municipality = municipality,
            cfdi = cfdi,
            email = email,
            paymentMethod = paymentMethod,
            status = status,
            quotationStepsId = quotationStepsId,
            currentStep = currentStep,
            deleted = deleted ?: false
        )
    }
}
