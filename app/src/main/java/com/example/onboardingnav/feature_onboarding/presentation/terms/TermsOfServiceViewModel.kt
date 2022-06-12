package com.example.onboardingnav.feature_onboarding.presentation.terms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onboardingnav.core.util.UiEvent
import com.example.onboardingnav.core.util.UiText
import com.example.onboardingnav.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TermsOfServiceViewModel@Inject constructor(): ViewModel() {
    var isAccepted = false

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onNextClick() {
        if (isAccepted) {
            viewModelScope.launch {
                _uiEvent.send(UiEvent.Success)
            }
        } else {
            viewModelScope.launch {
                _uiEvent.send(UiEvent.ShowSnackbar(message = UiText.StringResource(R.string.error_accept_terms)))
            }
        }
    }
}