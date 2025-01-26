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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
import com.print.color.printcolor.ui.settings.SettingsScreen
import com.print.color.printcolor.ui.theme.PrintColorTheme

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun PcSNavigationRail(
    productQuotationViewModel: ProductQuotationViewModel,
    //onAddQuotationSave: () -> Unit
) {
    val items = listOf(
        NavigationRailData(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            hasNews = false,
        ),
        NavigationRailData(
            title = "Settings",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
            hasNews = false,
        )
    )

    val context = LocalContext.current
    val navController = rememberNavController()
    val windowClass = calculateWindowSizeClass(context as Activity)
    val showNavigationRail =
        windowClass.widthSizeClass != WindowWidthSizeClass.Compact
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) { paddingValues ->
            Row(Modifier.padding(paddingValues)) {
                if (showNavigationRail) {
                    PcSNavigationSideBar(
                        items = items,
                        selectedItemIndex = selectedItemIndex,
                        onNavigate = { index ->
                            selectedItemIndex = index
                            when (items[index].title) {
                                "Profile" -> navController.navigate(Routes.Profile.route)
                                "Home" -> navController.navigate(Routes.Home.route)
                            }
                        },
                        navController = navController
                    )
                }
                NavHost(
                    navController = navController,
                    startDestination = Routes.Quotation.route,
                    modifier = Modifier.weight(1f)
                ) {
                    composable(Routes.Profile.route) {
                        ProfileScreen()
                    }
                    composable(Routes.Quotation.route) {
                        QuotationScreen(
                            productQuotationViewModel = productQuotationViewModel,
                            onAddQuotationSave = {}
                        )
                    }
                    composable(Routes.Settings.route) {
                        SettingsScreen()
                    }
                    composable(Routes.Home.route) {
                        HomeScreen()
                    }
                }
            }
        }
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
                    imageVector = Icons.Default.Add,
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
            imageVector = if (selected) item.selectedIcon else item.unselectedIcon,
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
