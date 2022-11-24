package com.chrispi.stoerungen.misc

import java.text.SimpleDateFormat
import java.util.*

object Helper {
    fun getDateFromString(dateString: String): Date {
        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        return formatter.parse(dateString)
            ?: throw Exception("Couldn't parse date - getDateFromString()")
    }
}