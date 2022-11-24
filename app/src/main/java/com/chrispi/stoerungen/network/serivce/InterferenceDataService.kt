package com.chrispi.stoerungen.network.serivce

import com.chrispi.stoerungen.network.dto.InterferenceDto
import retrofit2.Response
import retrofit2.http.GET

interface InterferenceDataService {
    @GET("trafficInfoList")
    suspend fun getInterferenceInformation() : Response<InterferenceDto>
}