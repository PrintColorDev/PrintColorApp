package com.print.color.printcolor.ui.components.NavigationRail.model

sealed class Screen(val route: String) {
    object Profile : Screen("profile")
    object Quotation : Screen("quotation")
    object Settings : Screen("settings")
}