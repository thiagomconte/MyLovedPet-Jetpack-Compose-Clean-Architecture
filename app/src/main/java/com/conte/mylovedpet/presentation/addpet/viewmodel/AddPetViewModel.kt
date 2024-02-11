package com.conte.mylovedpet.presentation.addpet.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AddPetViewModel : ViewModel(), AddPetUiAction {

    private val _channel = Channel<AddPetUiEvent>()
    val channel = _channel.receiveAsFlow()

    override fun onBack() {
        viewModelScope.launch(Dispatchers.Default) {
            _channel.send(AddPetUiEvent.OnBack)
        }
    }
}