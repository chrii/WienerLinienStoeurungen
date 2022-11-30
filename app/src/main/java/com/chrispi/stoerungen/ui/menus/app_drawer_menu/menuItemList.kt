package com.chrispi.stoerungen.ui.menus.app_drawer_menu

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import com.chrispi.stoerungen.R
import com.chrispi.stoerungen.models.MenuItem
import com.chrispi.stoerungen.navigation.Routes

val menuItemList: List<MenuItem> = listOf(
    MenuItem(
        icon = Icons.Default.Build,
        iconDescription = R.string.elevation_information_description,
        label = R.string.elevation_information,
        route = Routes.ElevatorInformationListRoute.route
    ),
    MenuItem(
        icon = Icons.Default.Build,
        iconDescription = R.string.interference_short_description,
        label = R.string.interference_short,
        route = Routes.InterferenceShortListRoute.route
    ),
    MenuItem(
        icon = Icons.Default.Build,
        iconDescription = R.string.interference_long_description,
        label = R.string.interference_long,
        route = Routes.InterferenceLongListRoute.route
    ),
)
