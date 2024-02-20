package com.conte.mylovedpet.presentation.addpetevent.viewmodel

interface AddPetEventUiAction {
    fun onBack()
    fun onEventNameTyping(value: String)
    fun onEventDateTyping(value: String)
    fun onEventTimeTyping(value: String)
    fun onSubmit()
}