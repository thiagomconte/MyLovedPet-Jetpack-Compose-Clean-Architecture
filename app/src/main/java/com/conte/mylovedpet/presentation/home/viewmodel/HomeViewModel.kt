package com.conte.mylovedpet.presentation.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.conte.domain.module.pet.usecase.GetAllPetsUseCase
import com.conte.mylovedpet.utils.update
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllPetsUseCase: GetAllPetsUseCase
) : ViewModel(), HomeUiAction {

    private val _uiState: MutableHomeUiState = MutableHomeUiState()
    val uiState: HomeUiState = _uiState

    private val _channel = Channel<HomeUiEvent>()
    val channel: Flow<HomeUiEvent> = _channel.receiveAsFlow()

    init {
        getPets()
    }

    override fun onAddPet() {
        viewModelScope.launch(Dispatchers.Default) {
            _channel.send(HomeUiEvent.OnAddPet)
        }
    }

    override fun onRetry() {
        getPets()
    }

    private fun getPets() {
        viewModelScope.launch(Dispatchers.IO) {
            getAllPetsUseCase().onSuccess {
                _uiState.update {
                    pets = it
                    error = false
                }
            }.onFailure {
                _uiState.update {
                    error = true
                    Log.e("HOM_VIEW_MODEL", it.message.orEmpty())
                }
            }
        }
    }

}