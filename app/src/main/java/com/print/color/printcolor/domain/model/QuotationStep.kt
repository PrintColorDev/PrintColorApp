package com.print.color.printcolor.domain.model

import androidx.annotation.DrawableRes

data class QuotationStep(
    val id: String,
    val stepKey: String,
    val stepValue: Boolean,
    @DrawableRes val quotationIcon: Int,
)
