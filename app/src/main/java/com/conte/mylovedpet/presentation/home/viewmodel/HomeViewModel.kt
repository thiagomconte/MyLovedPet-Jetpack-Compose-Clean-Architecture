package com.conte.mylovedpet.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.conte.domain.module.pet.model.Pet
import com.conte.domain.module.pet.usecase.GetAllPetsUseCase
import com.conte.mylovedpet.utils.update
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllPetsUseCase: GetAllPetsUseCase
) : ViewModel(), HomeUiAction {

    private val _uiState: MutableHomeUiState = MutableHomeUiState()
    val uiState: HomeUiState = _uiState

    init {
        getPets()
    }

    override fun onAddPet() {

    }

    override fun onRetry() {
        getPets()
    }

    private fun getPets() {
        viewModelScope.launch(Dispatchers.IO) {
            getAllPetsUseCase().onSuccess {
                _uiState.update {
                    pets = it
                    error = true
                }
            }.onFailure {
                _uiState.update {
                    error = true
                }
            }
        }
    }

}