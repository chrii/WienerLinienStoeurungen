package com.chrispi.stoerungen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.coroutineScope
import com.chrispi.stoerungen.models.ElevatorInformation
import com.chrispi.stoerungen.models.TrafficInterferenceLong
import com.chrispi.stoerungen.models.TrafficInterferenceShort
import com.chrispi.stoerungen.repositories.IfaceInterferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
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
    val loading: MutableState<Boolean> = mutableStateOf(false)
    val elevatorData: MutableState<List<ElevatorInformation>> = mutableStateOf(listOf())
    val trafficInterferenceShort: MutableState<List<TrafficInterferenceShort>> =
        mutableStateOf(listOf())
    val trafficInterferenceLong: MutableState<List<TrafficInterferenceLong>> =
        mutableStateOf(listOf())

    var timestamp: Date? = null
    var errorMessage = ""

    fun getData(
        lifecycle: Lifecycle,
    ) {
        loading.value = true
        lifecycle.coroutineScope.launch {
            val interferences = interferenceRepo.getInformation()
            if (interferences.hasData) {
                interferences.data!!.apply {
                    this@MainViewModel.elevatorData.value = elevatorInformation
                    this@MainViewModel.trafficInterferenceShort.value = trafficInterferenceShort
                    this@MainViewModel.trafficInterferenceLong.value = trafficInterferenceLong
                }
                Log.d("getData", "Got data")
                loading.value = false
            } else {
                errorMessage = interferences.message ?: "DataState has no Error message"
                loading.value = false
            }
        }
    }

    fun refreshData(lifecycle: Lifecycle) {
        val timer = Calendar.getInstance()
        timer.add(Calendar.MINUTE, 5)

        if (timestamp == null) {
            timestamp = Calendar.getInstance().time
            Log.d("Timestamp", timestamp.toString())
            getData(lifecycle)
        } else {
            if (timer.after(timestamp)) {
                Log.d("Timestamp", timer.time.toString())
            }
        }
    }
}