package com.conte.mylovedpet.presentation.home.viewmodel

interface HomeUiAction {
    fun onAddPet()
    fun onRetry()
    fun onPetClick(id: Int)

    companion object {
        fun buildFake() = object : HomeUiAction {
            override fun onAddPet() {}
            override fun onRetry() {}
            override fun onPetClick(id: Int) {}
        }
    }
}