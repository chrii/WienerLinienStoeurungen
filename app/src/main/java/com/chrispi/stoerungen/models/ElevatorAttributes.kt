package com.chrispi.stoerungen.models

import com.chrispi.stoerungen.network.dto.ElevatorAttributesDto
import com.google.gson.annotations.SerializedName
import java.util.Date

data class ElevatorAttributes(
    val blockedUntilDateAsHumanReadable: String,
    val blockedFromDateAsHumanReadable: String,
    val blockingReason: String,
    val relatedLines: List<String>,
    val affectedStationName: String,
    val locationOnStation: String,
    val affectedDirection: String,
    val relatedStops: List<Int>,
    val status: String,
)

