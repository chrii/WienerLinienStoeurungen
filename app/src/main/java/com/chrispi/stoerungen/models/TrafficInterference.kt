package com.chrispi.stoerungen.models

data class TrafficInterference(
    val categories: List<String>,
    val elevatorInformation: List<ElevatorInformation>,
    val trafficInterferenceLong: List<TrafficInterferenceLong>,
    val trafficInterferenceShort: List<TrafficInterferenceShort>
)
