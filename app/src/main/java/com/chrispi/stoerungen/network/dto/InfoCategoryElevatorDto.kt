package com.chrispi.stoerungen.network.dto

import com.google.gson.annotations.SerializedName

data class InfoCategoryElevatorDto(
    @SerializedName("refTrafficInfoCategoryId")
    val trafficCategoryId: Int?,

    @SerializedName("name")
    val trafficInfoListName: String?,

    @SerializedName("title")
    val interferenceTitle: String?,

    @SerializedName("description")
    val stationDescription: String?,

    @SerializedName("attributes")
    val attributes: ElevatorAttributesDto?,

    @SerializedName("time")
    val downTime: DowntimeDto?,

    @SerializedName("relatedLines")
    val relatedLines: List<String>?,

    @SerializedName("relatedStops")
    val relatedStopsAsRblNumber: List<Int>?
)
