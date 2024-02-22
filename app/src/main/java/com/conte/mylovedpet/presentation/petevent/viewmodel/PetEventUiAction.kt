package com.conte.mylovedpet.presentation.petevent.viewmodel

import com.conte.domain.module.petevent.model.PetEvent

interface PetEventUiAction {

    fun onBack()
    fun onAddEventClick()
    fun onDeleteClick(petEvent: PetEvent)

    companion object {
        fun buildFake() = object : PetEventUiAction {
            override fun onBack() {}
            override fun onAddEventClick() {}
            override fun onDeleteClick(petEvent: PetEvent) {}
        }
    }
}