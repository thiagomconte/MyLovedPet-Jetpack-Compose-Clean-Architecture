package com.conte.mylovedpet.utils

import androidx.compose.runtime.snapshots.Snapshot
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

private val mutex = Mutex()

interface Updatable

suspend fun <T : Updatable> T.update(block: T.() -> Unit): T = try {
    mutex.withLock {
        Snapshot.withMutableSnapshot { block(this) }
        this
    }
} catch (_: Exception) {
    this.update(block)
}