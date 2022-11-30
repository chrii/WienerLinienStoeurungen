package com.chrispi.stoerungen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chrispi.stoerungen.navigation.Routes
import com.chrispi.stoerungen.ui.elevator_information.ElevatorInformationListPage
import com.chrispi.stoerungen.ui.menus.app_drawer_menu.AppDrawerMenu
import com.chrispi.stoerungen.ui.menus.app_drawer_menu.AppDrawerScaffold
import com.chrispi.stoerungen.ui.menus.app_drawer_menu.menuItemList
import com.chrispi.stoerungen.ui.theme.WienerLinienStörungenTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.refreshData(lifecycle)
        val drawerMenu = AppDrawerMenu(menuItemList)

        setContent {
            val navController = rememberNavController()
            WienerLinienStörungenTheme {
                NavHost(
                    navController = navController,
                    startDestination = Routes.ElevatorInformationListRoute.route
                ) {

                    composable(Routes.ElevatorInformationListRoute.route) {
                        val viewModel: MainViewModel by viewModels()

                        AppDrawerScaffold(
                            appBarHeader = "Aufzugsstörungen",
                            drawerMenu = drawerMenu,
                            navigation = navController
                        ) {
                            ElevatorInformationListPage(viewModel)
                        }
                    }
                }
            }
        }
    }
}