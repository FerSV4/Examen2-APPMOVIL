package com.example.examenej2.usercard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class UserCardViewModel(
    initialNombre: String
) : ViewModel() {

    private val _state = MutableStateFlow(
        UserCardState(
            nombre = initialNombre,
            siguiendo = false
        )
    )
    val state: StateFlow<UserCardState> = _state

    fun handleIntent(intent: UserCardIntent) {
        when (intent) {
            UserCardIntent.ToggleFollow -> {
                _state.update { it.copy(siguiendo = !it.siguiendo) }
            }
        }
    }
}
