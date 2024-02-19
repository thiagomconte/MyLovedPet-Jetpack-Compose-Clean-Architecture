package com.conte.mylovedpet.utils

import androidx.compose.runtime.snapshots.Snapshot
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized


interface Updatable

@OptIn(InternalCoroutinesApi::class)
fun <T : Updatable> T.update(block: T.() -> Unit): T = try {
    synchronized(this) {
        Snapshot.withMutableSnapshot { block(this) }
        this
    }
} catch (_: Exception) {
    this.update(block)
}