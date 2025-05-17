package com.print.color.printcolor.utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

// Helper function to get string resource from Context
fun getStringResource(context: Context, @StringRes stringResId: Int): String {
    return context.getString(stringResId)
}

// Helper function to show toast
fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

// Helper function to format phone number
fun formatPhoneNumber(number: String): String {
    val digits = number.filter { it.isDigit() }

    if (digits.length != 10) return number

    val lada = digits.substring(0, 2)
    val middle = digits.substring(2, 6)
    val end = digits.substring(6, 10)

    return "$lada $middle $end"
}

// Helper function to convert millis to date
fun convertMillisToDate(millis: Long?): String {
    if (millis == null || millis == 0L) return ""
    val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    formatter.timeZone = TimeZone.getTimeZone("UTC")
    return formatter.format(Date(millis))
}