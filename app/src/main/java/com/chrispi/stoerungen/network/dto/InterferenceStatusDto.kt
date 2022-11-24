package com.chrispi.stoerungen.network.dto

import com.google.gson.annotations.SerializedName

data class InterferenceStatusDto(
    @SerializedName("value")
    val status: String?,

    @SerializedName("messageCode")
    val code: Int?,

    @SerializedName("serverTime")
    val serverTime: String?
)