package com.print.color.printcolor.ui.components.NavigationRail.model

import com.print.color.printcolor.R

/**
 * @see [com.print.color.printcolor.ui.profile.ProfileScreen]
 * @see [com.print.color.printcolor.ui.productQuotation.QuotationScreen]
 * @see [com.print.color.printcolor.ui.settings.SettingsScreen]
 * @see [com.print.color.printcolor.ui.home.HomeScreen]
 * */

sealed class Routes(val route: String) {
    object Profile : Routes(R.string.navigation_rail_profile.toString())
    object Quotation : Routes(R.string.navigation_rail_quotation.toString())
    object Settings : Routes(R.string.navigation_rail_settings.toString())
    object Home : Routes(R.string.navigation_rail_home.toString())
    object Quotations : Routes(R.string.navigation_rail_quotations.toString())
}