package com.conte.mylovedpet.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.conte.domain.module.commons.logError
import com.conte.domain.module.pet.usecase.FlowAllPetsUseCase
import com.conte.mylovedpet.utils.update
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val flowAllPetsUseCase: FlowAllPetsUseCase
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
        flowAllPetsUseCase()
            .onEach {
                _uiState.update {
                    pets = it
                    error = false
                }
            }
            .catch {
                _uiState.update {
                    error = true
                    logError { it.message }
                }
            }
            .launchIn(viewModelScope)
    }

}