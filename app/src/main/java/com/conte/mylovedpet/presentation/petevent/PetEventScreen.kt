package com.conte.mylovedpet.presentation.petevent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.conte.design_system.module.AppIcons
import com.conte.design_system.module.components.AppButton
import com.conte.design_system.module.components.AppIcon
import com.conte.design_system.module.components.AppText
import com.conte.design_system.module.components.AppTopBar
import com.conte.design_system.module.utils.Baseline4
import com.conte.design_system.module.utils.Baseline5
import com.conte.mylovedpet.R
import com.conte.mylovedpet.presentation.petevent.viewmodel.PetEventUiAction
import com.conte.mylovedpet.presentation.petevent.viewmodel.PetEventUiEvent
import com.conte.mylovedpet.presentation.petevent.viewmodel.PetEventUiState
import com.conte.mylovedpet.presentation.petevent.viewmodel.PetEventViewModel

@Composable
fun PetEventScreen(viewModel: PetEventViewModel = hiltViewModel(), navController: NavController) {

    val uiState = viewModel.uiState

    LaunchedEffect(Unit) {
        viewModel.channel.collect { event ->
            when (event) {
                PetEventUiEvent.OnBack -> navController.popBackStack()
            }
        }
    }

    PetEventScreen(viewModel = viewModel, uiState = uiState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetEventScreen(viewModel: PetEventUiAction, uiState: PetEventUiState) {
    Scaffold(
        topBar = {
            AppTopBar(
                title = stringResource(id = R.string.pet_event_nav_title),
                onMenuClick = { /*TODO*/ },
                onBackNavigation = viewModel::onBack
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = Baseline5)
                .verticalScroll(state = rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(Baseline4)
        ) {
            if (uiState.events.isEmpty()) {
                AppText(
                    modifier = Modifier.padding(top = Baseline5),
                    text = stringResource(id = R.string.pet_event_empty_label),
                    fontSize = 20.sp,
                    align = TextAlign.Center
                )
                AppIcon(painter = AppIcons.EventIcon, size = 40.dp)
                AppButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = stringResource(id = R.string.pet_event_btn_add)
                ) {
                    // TODO
                }
            } else {
                AppText(text = "Funcionou")
            }
        }
    }
}