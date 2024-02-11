package com.conte.design_system.module.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.conte.design_system.module.theme.AppColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String = "",
    titleSize: TextUnit = 20.sp,
    backgroundColor: Color = AppColor.Navy,
    contentColor: Color = AppColor.Peach,
    onMenuClick: () -> Unit,
    onBackNavigation: (() -> Unit)? = null,
) {
    TopAppBar(
        title = { AppText(text = title, color = contentColor, fontSize = titleSize) },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = backgroundColor),
        actions = {
            IconButton(onClick = onMenuClick) {
                AppIcon(
                    painter = rememberVectorPainter(image = Icons.Filled.Menu),
                    tint = contentColor
                )
            }
        },
        navigationIcon = {
            onBackNavigation?.let {
                IconButton(onClick = it) {
                    AppIcon(
                        painter = rememberVectorPainter(image = Icons.Filled.ArrowBack),
                        tint = contentColor
                    )
                }
            }
        }
    )
}