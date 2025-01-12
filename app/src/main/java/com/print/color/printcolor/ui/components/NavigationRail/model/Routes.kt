package com.print.color.printcolor.ui.components.NavigationRail.model
/**
 * @see [com.print.color.printcolor.ui.profile.ProfileScreen]
 * @see [com.print.color.printcolor.ui.productQuotation.QuotationScreen]
 * @see [com.print.color.printcolor.ui.settings.SettingsScreen]
 * @see [com.print.color.printcolor.ui.home.HomeScreen]
 * */
sealed class Routes(val route: String) {
    object Profile : Routes("profile")
    object Quotation : Routes("quotation")
    object Settings : Routes("settings")
    object Home : Routes("home")
}