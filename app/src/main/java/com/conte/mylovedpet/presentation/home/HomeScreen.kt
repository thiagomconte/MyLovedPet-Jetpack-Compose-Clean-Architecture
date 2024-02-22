package com.conte.mylovedpet.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.conte.design_system.module.AppIcons
import com.conte.design_system.module.components.AppButton
import com.conte.design_system.module.components.AppIcon
import com.conte.design_system.module.components.AppText
import com.conte.design_system.module.components.AppTopBar
import com.conte.design_system.module.theme.AppColor
import com.conte.design_system.module.utils.Baseline3
import com.conte.design_system.module.utils.Baseline4
import com.conte.design_system.module.utils.Baseline5
import com.conte.domain.module.pet.model.Pet
import com.conte.domain.module.pet.model.PetType
import com.conte.mylovedpet.R
import com.conte.mylovedpet.navigation.Navigation
import com.conte.mylovedpet.navigation.Navigation.PetEvent.navigateParams
import com.conte.mylovedpet.presentation.home.viewmodel.HomeUiAction
import com.conte.mylovedpet.presentation.home.viewmodel.HomeUiEvent
import com.conte.mylovedpet.presentation.home.viewmodel.HomeUiState
import com.conte.mylovedpet.presentation.home.viewmodel.HomeViewModel
import com.conte.mylovedpet.presentation.home.viewmodel.MutableHomeUiState
import com.conte.mylovedpet.utils.dateDifference
import com.conte.mylovedpet.utils.formatDate
import com.conte.mylovedpet.utils.isBirthday

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(), navController: NavHostController) {

    val uiState = viewModel.uiState

    LaunchedEffect(Unit) {
        viewModel.channel.collect { event ->
            when (event) {
                HomeUiEvent.OnAddPet -> {
                    navController.navigate(Navigation.AddPet.destination)
                }

                HomeUiEvent.OnSettings -> {
                    // TODO()
                }

                is HomeUiEvent.OnPetClick -> {
                    navController.navigate(Navigation.PetEvent.navigateParams(event.id))
                }
            }
        }
    }

    HomeScreen(viewModel = viewModel, uiState = uiState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeUiAction, uiState: HomeUiState) {
    Scaffold(
        topBar = {
            AppTopBar(onMenuClick = { /*TODO*/ })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = Baseline4),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(Baseline5))
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
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(Baseline4)) {
                        items(uiState.pets) {
                            PetCard(it) { petId ->
                                viewModel.onPetClick(petId)
                            }
                        }
                        item {
                            AppButton(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                text = stringResource(id = R.string.home_btn_add)
                            ) {
                                viewModel.onAddPet()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PetCard(pet: Pet, onClick: (id: Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable {
                onClick(pet.id)
            },
        colors = CardDefaults.cardColors(
            containerColor = if (pet.type == PetType.DOG) AppColor.Peach.copy(
                alpha = .6f
            ) else AppColor.SoftBlue.copy(alpha = .4f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = Baseline3,
                    vertical = Baseline4
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .background(
                        if (pet.type == PetType.DOG) AppColor.Peach else AppColor.SoftBlue,
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                AppIcon(
                    modifier = Modifier.padding(Baseline3),
                    painter = if (pet.type == PetType.DOG) AppIcons.DogOutlineIcon else AppIcons.CatOutlineIcon,
                    size = 32.dp
                )
            }
            Spacer(modifier = Modifier.width(Baseline3))
            Column(verticalArrangement = Arrangement.SpaceEvenly) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    AppIcon(
                        painter = AppIcons.PawIcon,
                        size = 16.dp
                    )
                    Spacer(modifier = Modifier.width(Baseline3))
                    AppText(text = pet.name, fontSize = 16.sp)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    AppIcon(
                        painter = AppIcons.CalendarIcon,
                        size = 16.dp,
                        tint = AppColor.Black
                    )
                    Spacer(modifier = Modifier.width(Baseline3))
                    AppText(
                        text = "${pet.birthday.formatDate()} - ${pet.birthday.dateDifference()}",
                        fontSize = 14.sp
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1F))
            if (pet.birthday.isBirthday()) {
                AppIcon(painter = AppIcons.PartyIcon, size = 24.dp)
            }
        }
    }
}

@Preview
@Composable
fun HomePreview() {
    HomeScreen(viewModel = HomeUiAction.buildFake(), uiState = MutableHomeUiState())
}

@Preview
@Composable
fun HomePreviewPopulated() {
    HomeScreen(viewModel = HomeUiAction.buildFake(), uiState = MutableHomeUiState.buildFake())
}
