package com.conte.design_system.module.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.conte.design_system.module.utils.SmallRoundedCornerShape

@Composable
fun AppIcon(
    modifier: Modifier = Modifier,
    painter: Painter,
    size: Dp = 24.dp,
    tint: Color = Color.Unspecified
) {
    Icon(
        modifier = modifier
            .size(size)
            .clip(SmallRoundedCornerShape),
        painter = painter,
        contentDescription = null,
        tint = tint
    )
}