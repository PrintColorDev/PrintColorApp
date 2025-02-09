package com.print.color.printcolor.data.response

import com.print.color.printcolor.domain.model.Quotation

data class QuotationResponse(
    val id: String = "",
    val clientName: String  = "",
    val customerName: String = "",
    val contact: String = "",
    val extraData: String = "",
    val isBillRequired: Boolean = false,
    val taxRegime: String = "",
    val rfc: String = "",
    val address: String = "",
    val zipCode: String = "",
    val state: String = "",
    val municipality: String = "",
    val cfdi: String = "",
    val email: String = "",
    val paymentMethod: String = "",
    val status: String = ""
) {
    fun toDomain(): Quotation {
        return Quotation(
            id = id,
            clientName = clientName,
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
            status = status
        )
    }

}
