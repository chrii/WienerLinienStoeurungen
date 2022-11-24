package com.chrispi.stoerungen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chrispi.stoerungen.navigation.Routes
import com.chrispi.stoerungen.ui.composables.AppDrawerScaffold
import com.chrispi.stoerungen.ui.composables.DotsFlashingLoadingAnimation
import com.chrispi.stoerungen.ui.theme.WienerLinienStörungenTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            WienerLinienStörungenTheme {
                NavHost(
                    navController = navController,
                    startDestination = Routes.Initializing.route
                ) {
                    composable(route = Routes.Initializing.route) {
                        val lifecycle = LocalLifecycleOwner.current.lifecycle
                        WienerLinienStörungenTheme {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                viewModel.getData(lifecycle)
                                DotsFlashingLoadingAnimation()
                                Text(
                                    text = "Hole Daten",
                                    style = MaterialTheme.typography.headlineMedium
                                )
                            }
                        }
                    }

                    composable(route = Routes.LandingPage.route) {
                        AppDrawerScaffold(
                            appBarHeader = "Header",
                            navigation = navController
                        ) {
                            Text("Hello")
                        }
                    }

                }
            }
        }
    }
}