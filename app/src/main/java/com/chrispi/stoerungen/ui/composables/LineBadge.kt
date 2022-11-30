package com.chrispi.stoerungen.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp

@Composable
fun LineBadge(line: String) {
    when {
        line.contains('U') -> {
            val color = when {
                line.contains("1") -> 0xFFE4131D
                line.contains("2") -> 0xFFA349A4
                line.contains("3") -> 0xFFFF7F27
                line.contains("4") -> 0xFF22B04C
                line.contains("5") -> 0xFF01BFBF
                else -> 0xFFA76745
            }
            Box(
                modifier = Modifier
                    .padding(1.dp)
                    .background(
                        Color(color),
                        CircleShape
                    )
            ) {
                Text(
                    modifier = Modifier
                        .padding(2.dp),
                    text = line,
                    color = Color.White
                )
            }
        }
        line.contains('A') || line.contains('B') -> {
            Box(
                modifier = Modifier
                    .padding(1.dp)
                    .background(
                        Color(0xFF025798),
                        RoundedCornerShape(20)
                    )
            ) {
                Text(
                    modifier = Modifier
                        .padding(2.dp),
                    text = line,
                    color = Color.White
                )
            }
        }
        else -> {
            Box(
                modifier = Modifier
                    .padding(1.dp)
                    .background(
                        Color(0xFF780100),
                        RoundedCornerShape(20)
                    )
            ) {
                Text(
                    modifier = Modifier
                        .padding(2.dp),
                    text = line,
                    color = Color.White
                )
            }
        }
    }
}