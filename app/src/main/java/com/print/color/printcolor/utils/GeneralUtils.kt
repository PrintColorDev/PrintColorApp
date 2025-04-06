package com.print.color.printcolor.utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

// Helper function to get string resource from Context
fun getStringResource(context: Context, @StringRes stringResId: Int): String {
    return context.getString(stringResId)
}

// Helper function to show toast
fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}