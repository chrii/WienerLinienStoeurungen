package com.chrispi.stoerungen.network.dto

import com.chrispi.stoerungen.misc.Helper
import com.chrispi.stoerungen.models.Downtime
import com.google.gson.annotations.SerializedName

data class DowntimeDto(
    @SerializedName("start")
    val startAsDateString: String?,

    @SerializedName("end")
    val endAsDateString: String?,

    @SerializedName("resume")
    val resumeAsDateString: String?,
) {
    companion object {
        fun getDtoFromMap(time: Map<String, Any>): DowntimeDto {
            return DowntimeDto(
                startAsDateString = time["start"] as String?,
                endAsDateString = time["end"] as String?,
                resumeAsDateString = time["resume"] as String?
            )
        }
    }
}

fun DowntimeDto.fromDto(): Downtime? {
    return if (!startAsDateString.isNullOrBlank() || !endAsDateString.isNullOrBlank()) {
        Downtime(
            start = Helper.getDateFromString(startAsDateString!!),
            end = Helper.getDateFromString(endAsDateString!!)
        )
    } else null
}