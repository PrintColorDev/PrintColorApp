package com.print.color.printcolor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.print.color.printcolor.ui.components.NavigationRail.PcSNavigationRail
import com.print.color.printcolor.ui.components.NavigationRail.model.NavigationRailData
import com.print.color.printcolor.ui.login.LoginScreenViewModel
import com.print.color.printcolor.ui.login.SignUpViewModel
import com.print.color.printcolor.ui.productQuotation.ProductQuotationViewModel
import com.print.color.printcolor.ui.quotationList.QuotationListViewModel
import com.print.color.printcolor.ui.theme.PrintColorTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val homeOption = R.string.navigation_rail_home.toString()
    val settingsOption = R.string.navigation_rail_settings.toString()
    val quotationsOption = R.string.navigation_rail_quotations.toString()
    val profileOption = R.string.navigation_rail_profile.toString()

    val routesList = listOf(
        homeOption, settingsOption, quotationsOption, profileOption
    )

    /** View Model Call's */
    val addProductViewModel: ProductQuotationViewModel by viewModels()
    val quotationViewModel: QuotationListViewModel by viewModels()
    val loginViewModel: LoginScreenViewModel by viewModels()
    val signUpViewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navigationRailItems = rememberNavigationRailItems()

            PrintColorTheme {
                Surface(modifier = Modifier.fillMaxSize()) {

                    //LoginScreen(loginScreenViewModel = loginViewModel, onLogin = {})
                    PcSNavigationRail(
                        productQuotationViewModel = addProductViewModel,
                        quotationListViewModel = quotationViewModel,
                        loginScreenViewModel = loginViewModel,
                        signUpViewModel = signUpViewModel,
                        navigationRailList = navigationRailItems,
                    )
                }
            }
        }
    }
}

/** NavigationRail list options*/
@Composable
fun rememberNavigationRailItems(): List<NavigationRailData> {
    return listOf(
        NavigationRailData(
            title = stringResource(R.string.navigation_rail_home),
            selectedIcon = painterResource(R.drawable.ic_pcs_home_filled),
            unselectedIcon = painterResource(R.drawable.ic_pcs_home_outlined),
            hasNews = false,
        ),
        NavigationRailData(
            title = stringResource(R.string.navigation_rail_settings),
            selectedIcon = painterResource(R.drawable.ic_pcs_settings_filled),
            unselectedIcon = painterResource(R.drawable.ic_pcs_settings_outlined),
            hasNews = false,
        ),
        NavigationRailData(
            title = stringResource(R.string.navigation_rail_quotations),
            selectedIcon = painterResource(R.drawable.ic_pcs_list_filled),
            unselectedIcon = painterResource(R.drawable.ic_pcs_list_outlined),
            hasNews = false,
        )
    )
}


