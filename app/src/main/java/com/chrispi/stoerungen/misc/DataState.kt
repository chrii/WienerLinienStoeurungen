package com.chrispi.stoerungen.misc

class DataState<T>(
    val hasData: Boolean,
    val data: T? = null,
    val message: String? = null
)