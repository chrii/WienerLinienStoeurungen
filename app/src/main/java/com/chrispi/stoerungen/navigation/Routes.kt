package com.chrispi.stoerungen.navigation

sealed class Routes(val route: String) {
    object InterferenceLongListRoute: Routes("traffic_interference_long_list_page")
    object InterferenceShortListRoute: Routes("traffic_interference_short_list_page")
    object ElevatorInformationListRoute: Routes("elevator_information_list_route")
    object InterferenceLongDetailsRoute: Routes("traffic_interference_long_details_page")
    object InterferenceShortDetailsRoute: Routes("traffic_interference_short_details_page")
    object ElevatorInformationDetailsRoute: Routes("elevator_information_details_route")
}