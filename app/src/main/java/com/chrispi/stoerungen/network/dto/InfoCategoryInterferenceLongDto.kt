package com.chrispi.stoerungen.network.dto

import com.google.gson.annotations.SerializedName

data class InfoCategoryInterferenceLongDto(
@SerializedName("refTrafficInfoCategoryId")
val trafficCategoryId: Int?,

@SerializedName("name")
val trafficInfoListName: String?,

@SerializedName("priority")
val priority: String?,

@SerializedName("owner")
val publicTransportationOwner: String?,

@SerializedName("title")
val interferenceTitle:String?,

@SerializedName("description")
val interferenceDescription:String?,

@SerializedName("time")
val downTime: DowntimeDto?,

@SerializedName("relatedLines")
val relatedLines: List<String>?,

@SerializedName("relatedStops")
val relatedStopsAsRblNumber: List<Int>?
)
