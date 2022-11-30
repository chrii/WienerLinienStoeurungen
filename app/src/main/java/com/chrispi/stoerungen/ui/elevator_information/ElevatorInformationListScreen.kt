package com.chrispi.stoerungen.ui.elevator_information

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.chrispi.stoerungen.MainViewModel
import com.chrispi.stoerungen.R
import com.chrispi.stoerungen.ui.composables.LoadingDialog

@Composable
fun ElevatorInformationListPage(
    viewModel: MainViewModel
) {
    if (viewModel.elevatorData.value.isNotEmpty()) {
        LazyColumn {
            items(viewModel.elevatorData.value) {

            }
        }
    }
    if (viewModel.loading.value) LoadingDialog(stringResource(id = R.string.loading_dialog_text))
}