package com.chrispi.stoerungen.network.dto

import com.google.gson.annotations.SerializedName

data class TrafficInfoCategoryGroup(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("name")
    val categoryName: String?
)