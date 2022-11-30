package com.chrispi.stoerungen.models

data class ElevatorInformation(
    val categoryTitle: String,
    val trafficInfoListName: String,
    val interferenceTitle: String,
    val stationDescription: String,
    val attributes: ElevatorAttributes?,
    val downTime: Downtime?,
    val relatedLines: List<String>,
    val relatedStopsAsRblNumber: List<Int>
)
