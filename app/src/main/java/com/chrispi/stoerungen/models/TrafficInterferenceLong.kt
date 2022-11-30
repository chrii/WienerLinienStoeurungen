package com.chrispi.stoerungen.models

data class TrafficInterferenceLong(
    val categoryTitle: String,
    val trafficInfoListName: String,
    val priority: String,
    val publicTransportationOwner: String,
    val interferenceTitle: String,
    val interferenceDescription: String,
    val downTime: Downtime?,
    val relatedLines: List<String>,
    val relatedStopsAsRblNumber: List<Int>
)
