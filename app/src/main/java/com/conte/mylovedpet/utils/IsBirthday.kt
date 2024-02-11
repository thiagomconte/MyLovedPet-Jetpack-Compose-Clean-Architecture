package com.conte.mylovedpet.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

fun isBirthday(date: String): Boolean {
    val format = DateTimeFormatter.ofPattern("ddMMyyyy", Locale.getDefault())
    return try {
        val parsedDate = LocalDate.parse(date, format)
        val now = LocalDate.now()
        parsedDate.isEqual(now)
    } catch (_: Exception) {
        false
    }
}