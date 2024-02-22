package com.conte.mylovedpet.presentation.addpetevent.viewmodel

interface AddPetEventUiAction {
    fun onBack()
    fun onEventNameTyping(value: String)
    fun onEventDateTyping(value: String)
    fun onEventTimeTyping(value: String)
    fun onAllowNotificationClick(value: Boolean)
    fun onSubmit()

    companion object {
        fun buildFake() = object : AddPetEventUiAction {
            override fun onBack() {}

            override fun onEventNameTyping(value: String) {}

            override fun onEventDateTyping(value: String) {}

            override fun onEventTimeTyping(value: String) {}

            override fun onAllowNotificationClick(value: Boolean) {}

            override fun onSubmit() {}
        }
    }
}