package com.conte.design_system.module.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.conte.design_system.module.theme.AppColor

@Composable
fun AppText(
    text: String,
    fontSize: TextUnit = 12.sp,
    color: Color = AppColor.Black,
    align: TextAlign = TextAlign.Start
) {
    Text(text = text, fontSize = fontSize, color = color, textAlign = align)
}