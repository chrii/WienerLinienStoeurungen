package com.chrispi.stoerungen.repositories

import com.chrispi.stoerungen.misc.DataState
import com.chrispi.stoerungen.models.TrafficInterference
import com.chrispi.stoerungen.network.dto.ElevatorAttributesDto
import com.chrispi.stoerungen.network.dto.InfoCategoryElevatorDto
import com.chrispi.stoerungen.network.dto.InterferenceDto

interface IfaceInterferenceRepository {
    suspend fun getInformation(): DataState<TrafficInterference>
}