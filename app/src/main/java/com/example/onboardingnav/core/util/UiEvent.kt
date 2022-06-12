package com.example.onboardingnav.core.util

sealed class UiEvent {
    object Success: UiEvent()
    object Logout: UiEvent()
    data class ShowSnackbar(val message: UiText): UiEvent()
}
