package com.print.color.printcolor.domain.model

data class Quotation(
    val id: String,
    val clientName: String,
    val customerName: String,
    val contact: String,
    val extraData: String,
    val isBillRequired: Boolean,
    val taxRegime: String,
    val rfc: String,
    val address: String,
    val zipCode: String,
    val state: String,
    val municipality: String,
    val cfdi: String,
    val email: String,
    val paymentMethod: String,
    val status: String,
    val quotationStepsId: String
)
