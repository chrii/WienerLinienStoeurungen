package com.chrispi.stoerungen.repositories.implementation

import android.util.Log
import com.chrispi.stoerungen.misc.DataState
import com.chrispi.stoerungen.models.TrafficInterference
import com.chrispi.stoerungen.network.dto.getCategoryTitleList
import com.chrispi.stoerungen.network.dto.getElevatorInformationList
import com.chrispi.stoerungen.network.dto.getInterferenceLongList
import com.chrispi.stoerungen.network.dto.getInterferenceShortList
import com.chrispi.stoerungen.network.serivce.InterferenceDataService
import com.chrispi.stoerungen.repositories.IfaceInterferenceRepository

class InterferenceRepositoryImpl(
    private val interferenceService: InterferenceDataService
) : IfaceInterferenceRepository {
    override suspend fun getInformation(): DataState<TrafficInterference> {
        try {
            val response = interferenceService.getInterferenceInformation()
            if (response.isSuccessful) {
                val information = response.body()
                    ?: throw Exception("Response Body is null - getInformation()")
                if (information.data != null) {
                    Log.d("getInformation", information.data.getInterferenceLongList().toString())
                    val interferenceData = TrafficInterference(
                        categories = information.data.getCategoryTitleList(),
                        elevatorInformation = information.data.getElevatorInformationList(),
                        trafficInterferenceLong = information.data.getInterferenceLongList(),
                        trafficInterferenceShort = information.data.getInterferenceShortList()
                    )
                    return DataState(
                        hasData = true,
                        data = interferenceData
                    )
                } else {
                    return DataState(
                        hasData = false,
                        message = "No Information Data"
                    )
                }
            } else {
                return DataState(hasData = false, message = "Response was not successful")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return DataState(hasData = false, message = e.stackTraceToString())
        }
    }
}