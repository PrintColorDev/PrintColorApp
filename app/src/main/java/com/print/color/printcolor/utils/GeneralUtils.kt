package com.print.color.printcolor.utils

import android.content.Context
import androidx.annotation.StringRes

// Helper function to get string resource from Context
fun getStringResource(context: Context, @StringRes stringResId: Int): String {
    return context.getString(stringResId)
}