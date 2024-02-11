package com.conte.mylovedpet.presentation.addpet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.conte.design_system.module.components.AppTopBar
import com.conte.design_system.module.utils.Baseline5
import com.conte.mylovedpet.R
import com.conte.mylovedpet.presentation.addpet.viewmodel.AddPetUiAction
import com.conte.mylovedpet.presentation.addpet.viewmodel.AddPetUiEvent
import com.conte.mylovedpet.presentation.addpet.viewmodel.AddPetViewModel

@Composable
fun AddPetScreen(viewModel: AddPetViewModel = hiltViewModel(), navController: NavController) {

    LaunchedEffect(Unit) {
        viewModel.channel.collect { event ->
            when (event) {
                AddPetUiEvent.OnBack -> navController.popBackStack()
            }
        }
    }

    AddPetScreen(viewModel = viewModel)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPetScreen(viewModel: AddPetUiAction) {
    Scaffold(
        topBar = {
            AppTopBar(
                title = stringResource(id = R.string.add_pet_nav_title),
                onMenuClick = { /*TODO*/ },
                onBackNavigation = viewModel::onBack
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(Baseline5),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
        }
    }
}

@Preview
@Composable
fun AddPetPreview() {
    AddPetScreen(viewModel = AddPetUiAction.buildFake())
}