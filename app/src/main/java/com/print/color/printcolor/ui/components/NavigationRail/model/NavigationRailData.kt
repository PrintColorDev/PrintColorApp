package com.print.color.printcolor.ui.components.NavigationRail.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * @param title The title to be displayed for the screen.
 * @param unselectedIcon The icon to be displayed for the screen when it is not selected.
 * @param selectedIcon The icon to be displayed for the screen when it is selected.
 * @param hasNews Whether the screen has news or not.
 * @param badgeCount The number of news the screen has.
 * */

data class NavigationRailData(
    val title: String,
    val unselectedIcon: Painter,
    val selectedIcon: Painter,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)
