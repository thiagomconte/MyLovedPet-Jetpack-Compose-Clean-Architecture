package com.conte.mylovedpet.utils

import com.conte.domain.module.commons.logError
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.Locale

fun String.formatDate(): String {
    val format = SimpleDateFormat("ddMMyyyy", Locale.getDefault())
    val wantedFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    return try {
        val date = format.parse(this)
        if (date != null) {
            wantedFormat.format(date)
        } else {
            ""
        }
    } catch (e: Exception) {
        logError { "Error to convert date: ${e.message}" }
        ""
    }
}

fun String.dateDifference(): String {
    val format = DateTimeFormatter.ofPattern("ddMMyyyy", Locale.getDefault())
    val date = LocalDate.parse(this, format)
    return if (date != null) {
        val difference = Period.between(date, LocalDate.now())
        "${difference.years}a ${difference.months}m ${difference.days}d"
    } else {
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

fun String?.toIntOrInvalidInt() = try {
    this?.toInt() ?: -1
} catch (_: NumberFormatException) {
    -1
}