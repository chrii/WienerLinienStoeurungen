package com.chrispi.stoerungen.ui.traffic_interference_long

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
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
fun InterferenceListLongPage(
    viewModel: MainViewModel
) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    if (viewModel.trafficInterferenceLong.value.isNotEmpty()) {
        LazyColumn(Modifier.fillMaxSize()) {
            items(viewModel.trafficInterferenceLong.value) { item ->
                Column {
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
        }
    } else {
        EmptyListFallback { viewModel.getData(lifecycle) }
    }
    if (viewModel.loading.value) LoadingDialog(stringResource(id = R.string.loading_dialog_text))
}