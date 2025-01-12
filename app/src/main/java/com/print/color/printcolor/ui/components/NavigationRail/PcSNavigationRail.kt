package com.print.color.printcolor.ui.components.NavigationRail

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.AddCircle
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.print.color.printcolor.ui.components.NavigationRail.model.NavigationRailData
import com.print.color.printcolor.ui.components.NavigationRail.model.Screen
import com.print.color.printcolor.ui.theme.PrintColorTheme


@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun PcSNavigationRail(modifier: Modifier = Modifier) {
    val items = listOf(
        NavigationRailData(
            title = "Profile",
            selectedIcon = Icons.Filled.AccountCircle,
            unselectedIcon = Icons.Outlined.AccountCircle,
            hasNews = false,
        ),
        NavigationRailData(
            title = "Quotation",
            selectedIcon = Icons.Filled.AddCircle,
            unselectedIcon = Icons.Outlined.AddCircle,
            hasNews = false,
            badgeCount = 45
        ),
        NavigationRailData(
            title = "Settings",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
            hasNews = true,
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
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            bottomBar = {
                //if (!showNavigationRail) {
                //    // NavigationBar()
                //}
            },
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
                                "Profile" -> navController.navigate(Screen.Profile.route)
                                "Quotation" -> navController.navigate(Screen.Quotation.route)
                                "Settings" -> navController.navigate(Screen.Settings.route)
                            }
                        }
                    )
                }
                NavHost(
                    navController = navController,
                    startDestination = Screen.Quotation.route,
                    modifier = Modifier.weight(1f)
                ) {
                    composable(Screen.Profile.route) {
                        ProfileScreen()
                    }
                    composable(Screen.Quotation.route) {
                        QuotationScreen()
                    }
                    composable(Screen.Settings.route) {
                        SettingsScreen()
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
    onNavigate: (Int) -> Unit
) {
    NavigationRail(
        header = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu"
                )
            }
            FloatingActionButton(
                onClick = { /*TODO*/ },
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
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

@Composable
fun ProfileScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Home Screen")
    }
}

@Composable
fun QuotationScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Chat Screen")
    }
}

@Composable
fun SettingsScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Settings Screen")
    }
}

@Preview(showBackground = true)
@Composable
fun PcSNavigationRailPreview() {
    PrintColorTheme {
        PcSNavigationRail()
    }
}
