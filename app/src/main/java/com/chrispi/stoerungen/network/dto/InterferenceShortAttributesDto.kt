package com.chrispi.stoerungen.network.dto

import com.chrispi.stoerungen.models.InterferenceLongAttributes
import com.google.gson.annotations.SerializedName

data class InterferenceShortAttributesDto(
    @SerializedName("relatedLineTypes")
    val relatedLineTypes: Map<String, String>?
) {
    companion object {
        fun convertToAttributesDto(attr: Map<String, Any>): InterferenceShortAttributesDto {
            return InterferenceShortAttributesDto(
                relatedLineTypes = attr["relatedLineTypes"] as Map<String, String>?
            )
        }
    }
}

fun InterferenceShortAttributesDto.fromDto(): InterferenceLongAttributes {
    return InterferenceLongAttributes(
        relatedLineTypes = relatedLineTypes ?: mapOf()
    )
}