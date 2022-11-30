package com.chrispi.stoerungen.ui.composables

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun EllipseDescription(
    content: String,
    cutAt: Int = 150
) {
    if(content.length >= cutAt) {
        Text(text = "${content.substring(0, cutAt)} [...]")
    } else {
        Text(content)
    }
}