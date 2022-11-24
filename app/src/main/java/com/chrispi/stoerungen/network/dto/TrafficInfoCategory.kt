package com.chrispi.stoerungen.network.dto

import com.google.gson.annotations.SerializedName

data class TrafficInfoCategory(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("refTrafficInfoCategoryGroupId")
    val trafficInfoCategoryGroupId: Int?,

    @SerializedName("name")
    val categoryName: String?,

    @SerializedName("trafficInfoNameList")
    val trafficInfoListName: String?,

    @SerializedName("title")
    val categoryTitle: String?
)