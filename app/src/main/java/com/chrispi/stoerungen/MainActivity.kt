package com.chrispi.stoerungen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chrispi.stoerungen.navigation.Routes
import com.chrispi.stoerungen.ui.elevator_information.ElevatorInformationListPage
import com.chrispi.stoerungen.ui.menus.app_drawer_menu.AppDrawerMenu
import com.chrispi.stoerungen.ui.menus.app_drawer_menu.AppDrawerScaffold
import com.chrispi.stoerungen.ui.menus.app_drawer_menu.menuItemList
import com.chrispi.stoerungen.ui.theme.WienerLinienSt√∂rungenTheme
import com.chrispi.stoerungen.ui.traffic_interference_long.InterferenceListLongPage
import com.chrispi.stoerungen.ui.traffic_interference_short.InterferenceListShortPage
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
            WienerLinienSt√∂rungenTheme {
                AppDrawerScaffold(
                    drawerMenu = drawerMenu,
                    navigation = navController
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Routes.ElevatorInformationListRoute.route
                    ) {
                        composable(Routes.ElevatorInformationListRoute.route) {
                            val viewModel: MainViewModel by viewModels()

                            ElevatorInformationListPage(viewModel)
                        }

                        composable(Routes.InterferenceShortListRoute.route) {
                            val viewModel: MainViewModel by viewModels()

                            InterferenceListShortPage(viewModel)
//                        }
                        }

                        composable(Routes.InterferenceLongListRoute.route) {
                            val viewModel: MainViewModel by viewModels()

                            InterferenceListLongPage(viewModel)
//                        }
                        }
                    }
                }
            }
        }
    }
}