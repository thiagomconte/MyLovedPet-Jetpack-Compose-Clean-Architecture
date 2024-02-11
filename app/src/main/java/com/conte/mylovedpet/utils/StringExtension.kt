package com.conte.mylovedpet.utils

import com.conte.domain.module.commons.logError
import java.text.SimpleDateFormat
import java.util.Locale

fun String.formatDate(): String {
    val format = SimpleDateFormat("ddMMyyyy", Locale.getDefault())
    val wantedFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    return try {
        val date = format.parse(this)
        wantedFormat.format(date)
    } catch (e: Exception) {
        logError { "Erro ao converter a data: ${e.message}" }
        ""
    }
}