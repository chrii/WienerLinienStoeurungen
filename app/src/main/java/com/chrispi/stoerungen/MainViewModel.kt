package com.chrispi.stoerungen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.coroutineScope
import androidx.navigation.NavController
import com.chrispi.stoerungen.misc.DataState
import com.chrispi.stoerungen.models.ElevatorInformation
import com.chrispi.stoerungen.models.TrafficInterference
import com.chrispi.stoerungen.models.TrafficInterferenceLong
import com.chrispi.stoerungen.models.TrafficInterferenceShort
import com.chrispi.stoerungen.navigation.Routes
import com.chrispi.stoerungen.repositories.IfaceInterferenceRepository
import com.chrispi.stoerungen.repositories.implementation.InterferenceRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class State {
    Main,
    Loading,
    Error
}

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val interferenceRepo: IfaceInterferenceRepository
) : ViewModel() {
    val elevatorData: MutableState<List<ElevatorInformation>> = mutableStateOf(listOf())
    val trafficInterferenceShort: MutableState<List<TrafficInterferenceShort>> =
        mutableStateOf(listOf())
    val trafficInterferenceLong: MutableState<List<TrafficInterferenceLong>> =
        mutableStateOf(listOf())

    var errorMessage = ""

    fun getData(
        lifecycle: Lifecycle,
    ) {
        lifecycle.coroutineScope.launch {
            val interferences = interferenceRepo.getInformation()
            if (interferences.hasData) {
                interferences.data!!.apply {
                    this@MainViewModel.elevatorData.value = elevatorInformation
                    this@MainViewModel.trafficInterferenceShort.value = trafficInterferenceShort
                    this@MainViewModel.trafficInterferenceLong.value = trafficInterferenceLong
                }
//                navigation.navigate(Routes.LandingPage.route) {
//                    popUpTo(Routes.Initializing.route) { inclusive = true }
//                }
            } else {
                errorMessage = interferences.message ?: "DataState has no Error message"
            }
        }
    }
}