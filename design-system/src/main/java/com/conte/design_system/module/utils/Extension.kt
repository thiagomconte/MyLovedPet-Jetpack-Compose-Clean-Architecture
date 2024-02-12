package com.conte.design_system.module.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Modifier.applyModifier(
    condition: Boolean,
    block: @Composable Modifier.() -> Modifier
): Modifier {
    return if (condition) {
        block()
    } else {
        this
    }
}