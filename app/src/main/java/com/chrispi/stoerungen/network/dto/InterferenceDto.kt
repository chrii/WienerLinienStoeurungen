package com.chrispi.stoerungen.network.dto

import com.google.gson.annotations.SerializedName

data class InterferenceDto(
    @SerializedName("data")
    val data: InterferenceDataDto?,

    @SerializedName("message")
    val status: InterferenceStatusDto?
)
