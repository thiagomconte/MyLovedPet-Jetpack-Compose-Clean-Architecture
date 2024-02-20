package com.conte.design_system.module.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class TimeTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return timeFilter(text)
    }
}

fun timeFilter(text: AnnotatedString): TransformedText {
    val trimmed = if (text.text.length >= 4) text.text.substring(0..3) else text.text
    var out = ""
    for (i in trimmed.indices) {
        out += trimmed[i]
        if (i == 1) out += ":"
    }

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 1) return offset
            if (offset <= 3) return offset + 1
            return 5
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 2) return offset
            if (offset <= 5) return offset - 1
            return 4
        }
    }

    return TransformedText(AnnotatedString(out), numberOffsetTranslator)
}
