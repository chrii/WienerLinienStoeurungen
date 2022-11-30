package com.chrispi.stoerungen.ui.elevator_information

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.chrispi.stoerungen.MainViewModel
import com.chrispi.stoerungen.R
import com.chrispi.stoerungen.ui.composables.EmptyListFallback
import com.chrispi.stoerungen.ui.composables.LineBadge
import com.chrispi.stoerungen.ui.composables.LoadingDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ElevatorInformationListPage(
    viewModel: MainViewModel
) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    if (viewModel.elevatorData.value.isNotEmpty()) {
        LazyColumn(Modifier.fillMaxSize()) {
            items(viewModel.elevatorData.value) { item ->
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
                    shadowElevation = 50.dp
                )
            }
        }
    } else {
        EmptyListFallback { viewModel.getData(lifecycle) }
    }
    if (viewModel.loading.value) LoadingDialog(stringResource(id = R.string.loading_dialog_text))
}