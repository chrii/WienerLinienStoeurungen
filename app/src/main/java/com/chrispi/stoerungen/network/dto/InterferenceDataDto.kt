package com.chrispi.stoerungen.network.dto

import com.chrispi.stoerungen.models.ElevatorInformation
import com.chrispi.stoerungen.models.TrafficInterferenceLong
import com.chrispi.stoerungen.models.TrafficInterferenceShort
import com.google.gson.annotations.SerializedName
import java.util.*

data class InterferenceDataDto(
    @SerializedName("trafficInfoCategoryGroups")
    val trafficInfoCategoryGroups: List<TrafficInfoCategoryGroup>?,

    @SerializedName("trafficInfoCategories")
    val trafficInfoCategories: List<TrafficInfoCategory>?,

    @SerializedName("trafficInfos")
    val trafficInformation: List<Map<String, Any>>?
) {
    companion object {
        fun getInfoCategoryElevatorDto(informationList: List<Map<String, Any>>): List<InfoCategoryElevatorDto> {
            return informationList.map {
                val attributes: Map<String, Any>? = it["attributes"] as? Map<String, Any>
                val downTime: Map<String, Any>? = it["time"] as? Map<String, Any>

                InfoCategoryElevatorDto(
                    trafficCategoryId = (it["refTrafficInfoCategoryId"] as Double).toInt(),
                    trafficInfoListName = it["name"] as String?,
                    interferenceTitle = it["title"] as String?,
                    stationDescription = it["description"] as String?,
                    attributes = attributes?.let { safeAttribute ->
                        ElevatorAttributesDto.convertElevatorAttributes(safeAttribute)
                    },
                    downTime = downTime?.let { safeDowntime ->
                        DowntimeDto.getDtoFromMap(safeDowntime)
                    },
                    relatedLines = it["relatedLines"] as List<String>?,
                    relatedStopsAsRblNumber = it["relatedStops"] as List<Int>?
                )
            }
        }

        fun getInfoCategoryInterferenceShort(informationList: List<Map<String, Any>>): List<InfoCategoryInterferenceShortDto> {
            return informationList.map {
                val attributes: Map<String, Any>? = it["attributes"] as? Map<String, Any>
                val downTime: Map<String, Any>? = it["time"] as? Map<String, Any>

                InfoCategoryInterferenceShortDto(
                    trafficCategoryId = (it["refTrafficInfoCategoryId"] as Double).toInt(),
                    trafficInfoListName = it["name"] as String?,
                    interferenceTitle = it["title"] as String?,
                    interferenceDescription = it["description"] as String?,
                    attributes = attributes?.let { safeAttribute ->
                        InterferenceShortAttributesDto.convertToAttributesDto(safeAttribute)
                    },
                    downTime = downTime?.let { safeDowntime ->
                        DowntimeDto.getDtoFromMap(safeDowntime)
                    },
                    relatedLines = it["relatedLines"] as List<String>?,
                    relatedStopsAsRblNumber = it["relatedStops"] as List<Int>?,
                    priority = it["priority"] as String?,
                    publicTransportationOwner = it["owner"] as String?,
                )
            }
        }

        fun getInfoCategoryInterferenceLong(informationList: List<Map<String, Any>>): List<InfoCategoryInterferenceLongDto> {
            return informationList.map { it ->
                val downTime: Map<String, Any>? = it["time"] as? Map<String, Any>
                val relatedStopsAsInt = (it["relatedStops"] as? List<Double>)
                    ?.map { stopItem -> stopItem.toInt() }

                InfoCategoryInterferenceLongDto(
                    trafficCategoryId = (it["refTrafficInfoCategoryId"] as Double).toInt(),
                    trafficInfoListName = it["name"] as String?,
                    interferenceTitle = it["title"] as String?,
                    interferenceDescription = it["description"] as String?,
                    downTime = downTime?.let { safeDowntime ->
                        DowntimeDto.getDtoFromMap(safeDowntime)
                    },
                    relatedLines = it["relatedLines"] as List<String>?,
                    relatedStopsAsRblNumber = relatedStopsAsInt,
                    priority = it["priority"] as String?,
                    publicTransportationOwner = it["owner"] as String?,
                )
            }
        }
    }
}

fun InterferenceDataDto.getCategoryTitleList(): List<String> {
    return trafficInfoCategories?.map { it.categoryTitle ?: "Unknown Category Title" }
        ?: listOf()
}

fun InterferenceDataDto.getElevatorInformationList(): List<ElevatorInformation> {
    val elevatorObject =
        trafficInformation?.filter { (it["refTrafficInfoCategoryId"] as Double).toInt() == 1 }
            ?: listOf()
    if (elevatorObject.isNotEmpty()) {
        val elevatorDtoList = InterferenceDataDto.getInfoCategoryElevatorDto(elevatorObject)
        val category = trafficInfoCategories?.find { it.id == 1 }?.categoryTitle
            ?: "No Category Title"
        return elevatorDtoList.map { elevatorDto ->
            ElevatorInformation(
                categoryTitle = category,
                trafficInfoListName = elevatorDto.trafficInfoListName
                    ?: "No Traffic Info List Name",
                interferenceTitle = elevatorDto.interferenceTitle ?: "No Interference Title",
                stationDescription = elevatorDto.stationDescription ?: "No Station Description",
                attributes = elevatorDto.attributes?.fromDto(),
                downTime = elevatorDto.downTime?.fromDto(),
                relatedLines = elevatorDto.relatedLines ?: listOf(),
                relatedStopsAsRblNumber = elevatorDto.relatedStopsAsRblNumber ?: listOf(),
            )
        }
    } else return listOf()
}

fun InterferenceDataDto.getInterferenceShortList(): List<TrafficInterferenceShort> {
    val elevatorObject =
        trafficInformation?.filter { (it["refTrafficInfoCategoryId"] as Double).toInt() == 2 }
            ?: listOf()
    if (elevatorObject.isNotEmpty()) {
        val interferenceDtoList =
            InterferenceDataDto.getInfoCategoryInterferenceShort(elevatorObject)
        val category = trafficInfoCategories?.find { it.id == 2 }?.categoryTitle
            ?: "No Category Title"
        return interferenceDtoList.map { interferenceDto ->
            TrafficInterferenceShort(
                categoryTitle = category,
                trafficInfoListName = interferenceDto.trafficInfoListName
                    ?: "No Traffic Info List Name",
                interferenceTitle = interferenceDto.interferenceTitle ?: "No Interference Title",
                interferenceDescription = interferenceDto.interferenceDescription
                    ?: "No Station Description",
                attributes = interferenceDto.attributes?.fromDto(),
                downTime = interferenceDto.downTime?.fromDto(),
                relatedLines = interferenceDto.relatedLines ?: listOf(),
                relatedStopsAsRblNumber = interferenceDto.relatedStopsAsRblNumber ?: listOf(),
                priority = interferenceDto.priority ?: "No priority found",
                publicTransportationOwner = interferenceDto.publicTransportationOwner
                    ?: "No owner found"
            )
        }
    } else return listOf()
}

fun InterferenceDataDto.getInterferenceLongList(): List<TrafficInterferenceLong> {
    val interferenceLong =
        trafficInformation?.filter { (it["refTrafficInfoCategoryId"] as Double).toInt() == 3 }
            ?: listOf()
    if (interferenceLong.isNotEmpty()) {
        val interferenceDtoList =
            InterferenceDataDto.getInfoCategoryInterferenceLong(interferenceLong)
        val category = trafficInfoCategories?.find { it.id == 3 }?.categoryTitle
            ?: "No Category Title"
        return interferenceDtoList.map { interferenceDto ->
            TrafficInterferenceLong(
                categoryTitle = category,
                trafficInfoListName = interferenceDto.trafficInfoListName
                    ?: "No Traffic Info List Name",
                interferenceTitle = interferenceDto.interferenceTitle ?: "No Interference Title",
                interferenceDescription = interferenceDto.interferenceDescription
                    ?: "No Station Description",
                downTime = interferenceDto.downTime?.fromDto(),
                relatedLines = interferenceDto.relatedLines ?: listOf(),
                relatedStopsAsRblNumber = interferenceDto.relatedStopsAsRblNumber ?: listOf(),
                priority = interferenceDto.priority ?: "No priority found",
                publicTransportationOwner = interferenceDto.publicTransportationOwner
                    ?: "No owner found"
            )
        }
    } else return listOf()
}