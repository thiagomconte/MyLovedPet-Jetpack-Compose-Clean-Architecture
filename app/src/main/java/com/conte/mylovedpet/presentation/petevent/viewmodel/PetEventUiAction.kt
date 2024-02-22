package com.conte.mylovedpet.presentation.petevent.viewmodel

interface PetEventUiAction {

    fun onBack()
    fun onAddEventClick()

    companion object {
        fun buildFake() = object : PetEventUiAction {
            override fun onBack() {}
            override fun onAddEventClick() {}
        }
    }
}