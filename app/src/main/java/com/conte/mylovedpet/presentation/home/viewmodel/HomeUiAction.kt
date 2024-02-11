package com.conte.mylovedpet.presentation.home.viewmodel

interface HomeUiAction {
    fun onAddPet()
    fun onRetry()

    companion object {
        fun buildFake() = object : HomeUiAction {
            override fun onAddPet() {}
            override fun onRetry() {}
        }
    }
}