package com.conte.mylovedpet.presentation.addpet.viewmodel

interface AddPetUiAction {
    fun onBack()

    companion object {
        fun buildFake() = object : AddPetUiAction {
            override fun onBack() {}
        }
    }
}