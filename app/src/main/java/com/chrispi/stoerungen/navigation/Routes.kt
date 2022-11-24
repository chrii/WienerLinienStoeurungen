package com.chrispi.stoerungen.navigation

sealed class Routes(val route: String) {
    object Initializing: Routes("initializing_route")
    object LandingPage: Routes("landing_page")
}