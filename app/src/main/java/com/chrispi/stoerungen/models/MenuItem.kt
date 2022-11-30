package com.chrispi.stoerungen.models

import android.graphics.drawable.Icon
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

data class MenuItem(
    val icon: ImageVector,
    @StringRes
    val iconDescription: Int,
    @StringRes
    val label: Int,
    val route: String
)
