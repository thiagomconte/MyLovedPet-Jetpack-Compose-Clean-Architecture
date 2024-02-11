package com.conte.mylovedpet.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.conte.design_system.module.AppIcons
import com.conte.design_system.module.components.AppButton
import com.conte.design_system.module.components.AppIcon
import com.conte.design_system.module.components.AppText
import com.conte.design_system.module.theme.AppColor
import com.conte.design_system.module.utils.Baseline4
import com.conte.design_system.module.utils.Baseline5
import com.conte.mylovedpet.R
import com.conte.mylovedpet.presentation.home.viewmodel.HomeUiAction
import com.conte.mylovedpet.presentation.home.viewmodel.HomeUiState
import com.conte.mylovedpet.presentation.home.viewmodel.HomeViewModel
import com.conte.mylovedpet.presentation.home.viewmodel.MutableHomeUiState

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {

    val uiState = viewModel.uiState

    HomeScreen(viewModel = viewModel, uiState = uiState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeUiAction, uiState: HomeUiState) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = AppColor.Navy),
                actions = {
                    AppIcon(
                        painter = rememberVectorPainter(image = Icons.Filled.Menu),
                        tint = AppColor.Peach
                    )
                }
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
            if (uiState.error) {
                AppIcon(
                    size = 80.dp,
                    painter = AppIcons.ErrorIcon
                )
                Spacer(modifier = Modifier.height(Baseline4))
                AppText(
                    text = stringResource(id = R.string.home_error_pets),
                    fontSize = 20.sp,
                    align = TextAlign.Center,
                )
                Spacer(modifier = Modifier.height(Baseline4))
                AppButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.home_btn_retry)
                ) {
                    viewModel.onRetry()
                }
            } else {
                if (uiState.pets.isEmpty()) {
                    AppIcon(
                        size = 120.dp,
                        painter = AppIcons.DogIcon
                    )
                    Spacer(modifier = Modifier.height(Baseline4))
                    AppText(
                        text = stringResource(id = R.string.home_empty_pets),
                        fontSize = 20.sp,
                        align = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.height(Baseline4))
                    AppButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.home_btn_add)
                    ) {
                        viewModel.onAddPet()
                    }
                } else {
                    AppText(
                        text = "Funcionou",
                        fontSize = 20.sp,
                        align = TextAlign.Center,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun HomePreview() {
    HomeScreen(viewModel = HomeUiAction.buildFake(), uiState = MutableHomeUiState())
}
