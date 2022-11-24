package com.chrispi.stoerungen.models

import com.chrispi.stoerungen.network.dto.DowntimeDto
import com.chrispi.stoerungen.network.dto.ElevatorAttributesDto
import com.google.gson.annotations.SerializedName

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
