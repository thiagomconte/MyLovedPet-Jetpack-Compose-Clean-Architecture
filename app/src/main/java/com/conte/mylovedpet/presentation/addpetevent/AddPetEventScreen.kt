package com.conte.mylovedpet.presentation.addpetevent

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.conte.design_system.module.components.AppButton
import com.conte.design_system.module.components.AppOutlineTextField
import com.conte.design_system.module.components.AppText
import com.conte.design_system.module.components.AppTopBar
import com.conte.design_system.module.components.VisualTransformationType
import com.conte.design_system.module.theme.AppColor
import com.conte.design_system.module.utils.Baseline4
import com.conte.design_system.module.utils.Baseline5
import com.conte.mylovedpet.PetEventWorker
import com.conte.mylovedpet.R
import com.conte.mylovedpet.presentation.addpetevent.viewmodel.AddPetEventUiAction
import com.conte.mylovedpet.presentation.addpetevent.viewmodel.AddPetEventUiEvent
import com.conte.mylovedpet.presentation.addpetevent.viewmodel.AddPetEventUiState
import com.conte.mylovedpet.presentation.addpetevent.viewmodel.AddPetEventViewModel

@Composable
fun AddPetEventScreen(
    viewModel: AddPetEventViewModel = hiltViewModel(),
    navController: NavController
) {

    val uiState = viewModel.uiState
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.channel.collect { event ->
            when (event) {
                AddPetEventUiEvent.OnBack -> navController.popBackStack()
                is AddPetEventUiEvent.OnAddPetEvent -> {
                    val inputData = Data.Builder()
                        .putString(PetEventWorker.KEY_NOTIFICATION_NAME, "Nome da Notificação")
                        .putString(
                            PetEventWorker.KEY_NOTIFICATION_DESCRIPTION,
                            "Descrição da Notificação"
                        )
                        .build()
                    val workRequest = OneTimeWorkRequestBuilder<PetEventWorker>()
                        .setInputData(inputData)
                        .setInitialDelay(
                            event.date.timeInMillis - System.currentTimeMillis(),
                            java.util.concurrent.TimeUnit.MILLISECONDS
                        )
                        .build()

                    WorkManager.getInstance(context).enqueue(workRequest)
                }
            }
        }
    }
    AddPetEventScreen(viewModel = viewModel, uiState = uiState)

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPetEventScreen(viewModel: AddPetEventUiAction, uiState: AddPetEventUiState) {
    Scaffold(
        topBar = {
            AppTopBar(
                title = stringResource(id = R.string.add_pet_event_nav_title),
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
            AppText(
                modifier = Modifier.padding(top = Baseline4),
                text = uiState.petName,
                fontSize = 24.sp
            )
            // EVENT NAME FIELD
            AppOutlineTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                borderColor = AppColor.Peach,
                value = uiState.eventName,
                label = stringResource(id = R.string.add_pet_event_label_name),
                onValueChange = { viewModel.onEventNameTyping(it) }
            )
            // EVENT DATE
            AppOutlineTextField(
                modifier = Modifier.fillMaxWidth(),
                borderColor = AppColor.Peach,
                value = uiState.eventDate,
                label = stringResource(id = R.string.add_pet_event_label_date),
                onValueChange = { viewModel.onEventDateTyping(it) },
                visualTransformationType = VisualTransformationType.DATE,
                keyboardType = KeyboardType.Decimal,
                isError = !uiState.validEventDate,
                placeholder = "__/__/____"
            )
            // EVENT TIME
            AppOutlineTextField(
                modifier = Modifier.fillMaxWidth(),
                borderColor = AppColor.Peach,
                value = uiState.eventTime,
                label = stringResource(id = R.string.add_pet_event_label_time),
                onValueChange = { viewModel.onEventTimeTyping(it) },
                visualTransformationType = VisualTransformationType.TIME,
                keyboardType = KeyboardType.Decimal,
                isError = !uiState.validEventTime,
                placeholder = "__:__"
            )
            AppButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.add_pet_event_btn_save),
                enabled = uiState.enableSaveButton
            ) {
                viewModel.onSubmit()
            }
        }
    }
}