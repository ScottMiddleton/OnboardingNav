package com.example.onboardingnav.feature_onboarding.presentation.pin

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onboardingnav.core.domain.preferences.Preferences
import com.example.onboardingnav.core.util.UiEvent
import com.example.onboardingnav.feature_onboarding.domain.use_cases.ConfirmPin
import com.example.onboardingnav.feature_onboarding.domain.use_cases.ValidatePin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConfirmPinViewModel @Inject constructor(
    private val preferences: Preferences,
    private val confirmPinUseCase: ConfirmPin,
    state: SavedStateHandle
) : ViewModel() {

    var pin = state.get<String>("pin")
    var confirmedPin = ""


    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    fun onNextClick() {
        when (val result = confirmPinUseCase(pin = pin, confirmedPin = confirmedPin)) {
            is ValidatePin.PinResult.Success -> {
                preferences.savePin(result.pin)
                preferences.saveShouldShowOnboarding(false)
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.Success)
                }
            }
            is ValidatePin.PinResult.Error -> {
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.ShowSnackbar(result.message))
                }

            }
        }
    }
}