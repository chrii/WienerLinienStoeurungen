package com.chrispi.stoerungen.network.dto

import com.chrispi.stoerungen.models.ElevatorAttributes
import com.google.gson.annotations.SerializedName

data class ElevatorAttributesDto(
    @SerializedName("ausBis")
    val blockedUntilDateAsHumanReadable: String?,

    @SerializedName("ausVon")
    val blockedFromDateAsHumanReadable: String?,

    @SerializedName("reason")
    val blockingReason: String?,

    @SerializedName("relatedLines")
    val relatedLines: List<String>?,

    @SerializedName("station")
    val affectedStationName: String?,

    @SerializedName("location")
    val locationOnStation: String?,

    @SerializedName("towards")
    val affectedDirection: String?,

    @SerializedName("relatedStops")
    val relatedStops: List<Int>?,

    @SerializedName("status")
    val status: String?,
) {
    companion object {
        fun convertElevatorAttributes(attr: Map<String, Any>): ElevatorAttributesDto {
            return ElevatorAttributesDto(
                blockedUntilDateAsHumanReadable = attr["ausBis"] as String?,
                blockedFromDateAsHumanReadable = attr["ausVon"] as String?,
                blockingReason = attr["reason"] as String?,
                relatedLines = attr["relatedLines"] as List<String>?,
                affectedStationName = attr["station"] as String?,
                locationOnStation = attr["location"] as String?,
                affectedDirection = attr["towards"] as String?,
                relatedStops = attr["relatedStops"] as List<Int>?,
                status = attr["status"] as String?
            )
        }
    }
}

fun ElevatorAttributesDto.fromDto(): ElevatorAttributes = ElevatorAttributes(
    blockedUntilDateAsHumanReadable = blockedUntilDateAsHumanReadable ?: "No date found",
    blockedFromDateAsHumanReadable = blockedFromDateAsHumanReadable ?: "No date found",
    blockingReason = blockingReason ?: "No blocking reason found",
    relatedLines = relatedLines ?: listOf(),
    affectedStationName = affectedStationName ?: "No station name found",
    locationOnStation = locationOnStation ?: "No location found",
    affectedDirection = affectedDirection ?: "No affected direction found",
    relatedStops = relatedStops ?: listOf(),
    status = status ?: "No status found",
)