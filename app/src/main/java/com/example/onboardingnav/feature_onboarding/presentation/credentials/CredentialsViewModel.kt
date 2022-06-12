package com.example.onboardingnav.feature_onboarding.presentation.credentials

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onboardingnav.core.domain.preferences.Preferences
import com.example.onboardingnav.feature_onboarding.domain.use_cases.ValidateCredentials
import com.example.onboardingnav.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CredentialsViewModel @Inject constructor(
    private val preferences: Preferences,
    private val validateUseCase: ValidateCredentials
): ViewModel() {

    var email = ""
    var password = ""

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onNextClick() {
        when(val result = validateUseCase(email, password)) {
            is ValidateCredentials.Result.Success -> {
                preferences.saveEmail(result.email)
                preferences.savePassword(result.password)
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.Success)
                }
            }
            is ValidateCredentials.Result.Error -> {
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.ShowSnackbar(result.message))
                }

            }
        }
    }

}