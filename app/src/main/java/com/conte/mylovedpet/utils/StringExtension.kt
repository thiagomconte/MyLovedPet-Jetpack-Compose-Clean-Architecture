package com.conte.mylovedpet.utils

import com.conte.domain.module.commons.logError
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

fun String.formatDate(): String {
    val format = SimpleDateFormat("ddMMyyyy", Locale.getDefault())
    val wantedFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    return try {
        val date = format.parse(this)
        wantedFormat.format(date)
    } catch (e: Exception) {
        logError { "Error to convert date: ${e.message}" }
        ""
    }
}

fun String.isBirthday(): Boolean {
    val format = DateTimeFormatter.ofPattern("ddMMyyyy", Locale.getDefault())
    return try {
        val parsedDate = LocalDate.parse(this, format)
        val now = LocalDate.now()
        parsedDate.isEqual(now)
    } catch (e: Exception) {
        logError { "Error to convert date: ${e.message}" }
        false
    }
}