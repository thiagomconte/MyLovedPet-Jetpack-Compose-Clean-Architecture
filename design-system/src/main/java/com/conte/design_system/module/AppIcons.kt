package com.conte.design_system.module

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.conte.design_system.R

object AppIcons {

    val DogIcon: Painter
        @Composable
        get() = painterResource(id = R.drawable.dog_icon)

    val ErrorIcon: Painter
        @Composable
        get() = painterResource(id = R.drawable.error_icon)

    val AlertIcon: Painter
        @Composable
        get() = painterResource(id = R.drawable.alert_icon)

    val DogOutlineIcon: Painter
        @Composable
        get() = painterResource(id = R.drawable.dog_outline_icon)

    val CatOutlineIcon: Painter
        @Composable
        get() = painterResource(id = R.drawable.cat_outline_icon)

    val PartyIcon: Painter
        @Composable
        get() = painterResource(id = R.drawable.party_icon)
}