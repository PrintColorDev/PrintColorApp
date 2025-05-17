package com.print.color.printcolor.domain.model

import java.util.Date

data class User(
    val userId: String,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val userDate: Date,
    val pin: String,
    val userName: String
)