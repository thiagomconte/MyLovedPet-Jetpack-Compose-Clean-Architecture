package com.conte.domain.module.commons

import android.util.Log

fun Any.logError(content: () -> String?) {
    Log.e(this::class.simpleName, content.invoke().orEmpty())
}

fun Any.logInfo(content: () -> String?) {
    Log.i(this::class.simpleName, content.invoke().orEmpty())
}