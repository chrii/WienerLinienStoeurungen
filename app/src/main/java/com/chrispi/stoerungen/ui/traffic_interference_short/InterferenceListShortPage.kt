package com.chrispi.stoerungen.ui.traffic_interference_short

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import com.chrispi.stoerungen.MainViewModel
import com.chrispi.stoerungen.R
import com.chrispi.stoerungen.ui.composables.EllipseDescription
import com.chrispi.stoerungen.ui.composables.EmptyListFallback
import com.chrispi.stoerungen.ui.composables.LineBadge
import com.chrispi.stoerungen.ui.composables.LoadingDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InterferenceListShortPage(
    viewModel: MainViewModel
) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    Log.d("List", viewModel.elevatorData.value.toString())
    if (viewModel.trafficInterferenceShort.value.isNotEmpty()) {
        LazyColumn {
            items(viewModel.trafficInterferenceShort.value) { item ->
                ListItem(
                    headlineText = {
                        Text(text = item.interferenceTitle)
                    },
                    overlineText = {
                        Row {
                            item.relatedLines.forEach {
                                LineBadge(line = it)
                            }
                        }
                    },
                    supportingText = { EllipseDescription(item.interferenceDescription) }
                )
            }
        }
    } else {
        EmptyListFallback { viewModel.getData(lifecycle) }
    }
    if (viewModel.loading.value) LoadingDialog(stringResource(id = R.string.loading_dialog_text))
}