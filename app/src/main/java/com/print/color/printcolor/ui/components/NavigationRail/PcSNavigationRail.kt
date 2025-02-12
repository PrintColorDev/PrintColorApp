package com.print.color.printcolor.ui.components.NavigationRail

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.print.color.printcolor.ui.components.NavigationRail.model.NavigationRailData
import com.print.color.printcolor.ui.components.NavigationRail.model.Routes
import com.print.color.printcolor.ui.home.HomeScreen
import com.print.color.printcolor.ui.productQuotation.ProductQuotationViewModel
import com.print.color.printcolor.ui.productQuotation.QuotationScreen
import com.print.color.printcolor.ui.profile.ProfileScreen
import com.print.color.printcolor.ui.quotationList.QuotationListScreen
import com.print.color.printcolor.ui.quotationList.QuotationListViewModel
import com.print.color.printcolor.ui.settings.SettingsScreen
import com.print.color.printcolor.ui.theme.PrintColorTheme
import com.print.color.printcolor.R

@Composable
fun NavigationGraph(
    navController: NavHostController,
    productQuotationViewModel: ProductQuotationViewModel,
    quotationListViewModel: QuotationListViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Quotation.route,
        //modifier = Modifier.weight(1f)
    ) {
        composable(Routes.Profile.route) { ProfileScreen() }
        composable(Routes.Quotation.route) {
            QuotationScreen(
                productQuotationViewModel = productQuotationViewModel,
                onAddQuotationSave = {}
            )
        }
        composable(Routes.Settings.route) { SettingsScreen() }
        composable(Routes.Home.route) { HomeScreen() }
        composable(Routes.Quotations.route) {
            QuotationListScreen(quotationListViewModel = quotationListViewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun shouldShowNavigationRail(): Boolean {
    val context = LocalContext.current
    val windowClass = calculateWindowSizeClass(context as Activity)
    return windowClass.widthSizeClass != WindowWidthSizeClass.Compact
}

@Composable
fun PcSNavigationRail(
    productQuotationViewModel: ProductQuotationViewModel,
    quotationListViewModel: QuotationListViewModel,
    navigationRailList: List<NavigationRailData>
) {
    val navController = rememberNavController()
    val showNavigationRail = shouldShowNavigationRail()
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }

    val homeOption = stringResource(R.string.navigation_rail_home)
    val settingsOption = stringResource(R.string.navigation_rail_settings)
    val quotationsOption = stringResource(R.string.navigation_rail_quotations)
    val profileOption = stringResource(R.string.navigation_rail_profile)

    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
            Row(Modifier.padding(paddingValues)) {
                if (showNavigationRail) {
                    PcSNavigationSideBar(
                        items = navigationRailList,
                        selectedItemIndex = selectedItemIndex,
                        onNavigate = { index ->
                            selectedItemIndex = index
                            navigateToRoute(
                                navController,
                                navigationRailList[index].title,
                                homeOption,
                                settingsOption,
                                quotationsOption,
                                profileOption
                            )
                        },
                        navController = navController
                    )
                }
                NavigationGraph(
                    navController = navController,
                    productQuotationViewModel = productQuotationViewModel,
                    quotationListViewModel = quotationListViewModel
                )
            }
        }
    }
}

private fun navigateToRoute(
    navController: NavController,
    route: String,
    homeOption: String,
    settingsOption: String,
    quotationsOption: String,
    profileOption: String
) {
    when (route) {
        profileOption -> navController.navigate(Routes.Profile.route)
        homeOption -> navController.navigate(Routes.Home.route)
        settingsOption -> navController.navigate(Routes.Settings.route)
        quotationsOption -> navController.navigate(Routes.Quotations.route)
    }
}

@Composable
fun PcSNavigationSideBar(
    items: List<NavigationRailData>,
    selectedItemIndex: Int,
    onNavigate: (Int) -> Unit,
    navController: NavHostController
) {
    NavigationRail(
        header = {
            IconButton(onClick = {
                navController.navigate(Routes.Profile.route)
            }) {
                Icon(
                    imageVector = Icons.Default.Face,
                    contentDescription = "Profile"
                )
            }
            FloatingActionButton(
                onClick = {
                    navController.navigate(Routes.Quotation.route)
                },
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_pcs_add),
                    contentDescription = "Add Quotation"
                )
            }
        },
        modifier = Modifier
            .background(MaterialTheme.colorScheme.inverseOnSurface)
            .offset(x = (-1).dp)
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Bottom)
        ) {
            items.forEachIndexed { index, item ->
                NavigationRailItem(
                    selected = selectedItemIndex == index,
                    onClick = {
                        onNavigate(index)
                    },
                    icon = {
                        NavigationIcon(
                            item = item,
                            selected = selectedItemIndex == index
                        )
                    },
                    label = {
                        Text(text = item.title)
                    },
                )
            }
        }
    }
}

@Composable
fun NavigationIcon(
    item: NavigationRailData,
    selected: Boolean
) {
    BadgedBox(
        badge = {
            if (item.badgeCount != null) {
                Badge {
                    Text(text = item.badgeCount.toString())
                }
            } else if (item.hasNews) {
                Badge()
            }
        }
    ) {
        Icon(
            painter = if (selected) item.selectedIcon else item.unselectedIcon,
            contentDescription = item.title
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PcSNavigationRailPreview() {
    PrintColorTheme {
        //PcSNavigationRail()
    }
}
