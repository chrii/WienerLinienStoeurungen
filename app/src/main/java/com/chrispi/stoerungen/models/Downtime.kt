package com.chrispi.stoerungen.models

import com.google.gson.annotations.SerializedName
import java.util.Date

class Downtime(
    val start: Date,
    val end: Date,
)