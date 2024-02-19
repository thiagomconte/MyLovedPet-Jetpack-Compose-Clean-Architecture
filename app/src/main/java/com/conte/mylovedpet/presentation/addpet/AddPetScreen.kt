package com.conte.mylovedpet.presentation.addpet

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.conte.design_system.module.AppIcons
import com.conte.design_system.module.components.AppButton
import com.conte.design_system.module.components.AppIcon
import com.conte.design_system.module.components.AppOutlineTextField
import com.conte.design_system.module.components.AppText
import com.conte.design_system.module.components.AppTopBar
import com.conte.design_system.module.components.VisualTransformationType
import com.conte.design_system.module.theme.AppColor
import com.conte.design_system.module.utils.Baseline2
import com.conte.design_system.module.utils.Baseline4
import com.conte.design_system.module.utils.Baseline5
import com.conte.design_system.module.utils.applyModifier
import com.conte.domain.module.pet.model.PetGender
import com.conte.domain.module.pet.model.PetType
import com.conte.mylovedpet.R
import com.conte.mylovedpet.presentation.addpet.viewmodel.AddPetUiAction
import com.conte.mylovedpet.presentation.addpet.viewmodel.AddPetUiEvent
import com.conte.mylovedpet.presentation.addpet.viewmodel.AddPetUiState
import com.conte.mylovedpet.presentation.addpet.viewmodel.AddPetViewModel
import com.conte.mylovedpet.presentation.addpet.viewmodel.MutableAddPetUiState

@Composable
fun AddPetScreen(viewModel: AddPetViewModel = hiltViewModel(), navController: NavController) {

//    val galleryLauncher =
//        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
//            uri?.let {
//                viewModel.onImageSelected(it)
//            }
//        }

    LaunchedEffect(Unit) {
        viewModel.channel.collect { event ->
            when (event) {
                AddPetUiEvent.OnBack,
                AddPetUiEvent.OnSubmit -> navController.popBackStack()

                AddPetUiEvent.OnSelectImage -> {
//                    galleryLauncher.launch("image/*")
                }
            }
        }
    }

    AddPetScreen(viewModel = viewModel, uiState = viewModel.uiState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPetScreen(viewModel: AddPetUiAction, uiState: AddPetUiState) {
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
                .padding(horizontal = Baseline5)
                .verticalScroll(state = rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(Baseline4)
        ) {
            Spacer(modifier = Modifier.width(Baseline5))
            AppText(
                text = stringResource(id = R.string.add_pet_select_type),
                fontSize = 20.sp,
                align = TextAlign.Center,
            )
            SelectAvatar(uiState = uiState, onAvatarClick = { viewModel.onAvatarClick(it) })
            // PET NAME
            AppOutlineTextField(
                modifier = Modifier.fillMaxWidth(),
                borderColor = if (uiState.petType == PetType.DOG) AppColor.Peach else AppColor.SoftBlue,
                value = uiState.name,
                label = stringResource(id = R.string.add_pet_label_name),
                onValueChange = { viewModel.onNameTyping(it) }
            )
            // PET BIRTHDAY
            AppOutlineTextField(
                modifier = Modifier.fillMaxWidth(),
                borderColor = if (uiState.petType == PetType.DOG) AppColor.Peach else AppColor.SoftBlue,
                value = uiState.birthday,
                label = stringResource(id = R.string.add_pet_label_birthday),
                onValueChange = { viewModel.onBirthdayTyping(it) },
                visualTransformationType = VisualTransformationType.DATE,
                keyboardType = KeyboardType.Decimal,
                isError = !uiState.validBirthday
            )
            // PET BREED
            AppOutlineTextField(
                modifier = Modifier.fillMaxWidth(),
                borderColor = if (uiState.petType == PetType.DOG) AppColor.Peach else AppColor.SoftBlue,
                value = uiState.breed,
                label = stringResource(id = R.string.add_pet_label_breed),
                onValueChange = { viewModel.onBreedTyping(it) }
            )
            // PET GENDER
            AppText(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.add_pet_label_gender),
                fontSize = 18.sp
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = uiState.petGender == PetGender.MALE,
                        onClick = { viewModel.onPetGenderClick(PetGender.MALE) },
                        colors = RadioButtonDefaults.colors(selectedColor = if (uiState.petType == PetType.DOG) AppColor.Peach else AppColor.SoftBlue)
                    )
                    Spacer(modifier = Modifier.width(Baseline2))
                    AppText(
                        text = stringResource(id = R.string.add_pet_label_gender_male),
                        fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.width(Baseline4))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = uiState.petGender == PetGender.FEMALE,
                        onClick = { viewModel.onPetGenderClick(PetGender.FEMALE) },
                        colors = RadioButtonDefaults.colors(selectedColor = if (uiState.petType == PetType.DOG) AppColor.Peach else AppColor.SoftBlue)
                    )
                    Spacer(modifier = Modifier.width(Baseline2))
                    AppText(
                        text = stringResource(id = R.string.add_pet_label_gender_female),
                        fontSize = 16.sp
                    )
                }
            }
            AppButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.add_pet_btn_submit),
                enabled = uiState.enableSubmitButton
            ) {
                viewModel.onSubmit()
            }
            Spacer(modifier = Modifier.height(Baseline5))
        }
    }
}

@Composable
fun SelectAvatar(uiState: AddPetUiState, onAvatarClick: (type: PetType) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(AppColor.Peach, CircleShape)
                .applyModifier(condition = uiState.petType == PetType.DOG) {
                    this.border(4.dp, AppColor.Black, CircleShape)
                }
                .clickable { onAvatarClick(PetType.DOG) },
            contentAlignment = Alignment.Center
        ) {
            AppIcon(
                modifier = Modifier.padding(Baseline4),
                painter = AppIcons.DogOutlineIcon,
                size = 80.dp
            )
        }
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(AppColor.SoftBlue, CircleShape)
                .applyModifier(condition = uiState.petType == PetType.CAT) {
                    this.border(4.dp, AppColor.Black, CircleShape)
                }
                .clickable { onAvatarClick(PetType.CAT) },
            contentAlignment = Alignment.Center
        ) {
            AppIcon(
                modifier = Modifier.padding(Baseline4),
                painter = AppIcons.CatOutlineIcon,
                size = 80.dp
            )
        }
    }
}

@Preview
@Composable
fun AddPetPreview() {
    AddPetScreen(viewModel = AddPetUiAction.buildFake(), uiState = MutableAddPetUiState())
}