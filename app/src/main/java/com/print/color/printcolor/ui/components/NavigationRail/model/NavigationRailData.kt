package com.print.color.printcolor.ui.components.NavigationRail.model

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationRailData(
    val title: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)
