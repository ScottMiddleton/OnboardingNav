package com.example.onboardingnav.feature_onboarding.presentation.pin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onboardingnav.core.util.UiEvent
import com.example.onboardingnav.feature_onboarding.domain.use_cases.ValidatePin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewPinViewModel @Inject constructor(
    private val validatePinUseCase: ValidatePin
): ViewModel() {

    var pin = ""

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    fun onNextClick() {
        when(val result = validatePinUseCase(pin)) {
            is ValidatePin.PinResult.Success -> {
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