package com.conte.design_system.module

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.conte.design_system.R

object AppIcons {

    val DogIcon: Painter
        @Composable
        get() = painterResource(id = R.drawable.dog_icon)
}