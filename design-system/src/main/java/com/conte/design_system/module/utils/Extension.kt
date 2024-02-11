package com.conte.design_system.module.utils

import androidx.compose.ui.Modifier

fun Modifier.applyModifier(condition: Boolean, block: Modifier.() -> Modifier): Modifier {
    return if (condition) {
        block()
    } else {
        this
    }
}