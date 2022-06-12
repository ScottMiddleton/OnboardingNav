package com.example.onboardingnav.feature_onboarding.presentation.personal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onboardingnav.core.domain.preferences.Preferences
import com.example.onboardingnav.core.util.UiEvent
import com.example.onboardingnav.feature_onboarding.domain.use_cases.ValidatePersonalInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonalInfoViewModel @Inject constructor(
    private val preferences: Preferences,
    private val validateUseCase: ValidatePersonalInfo
): ViewModel() {

    var firstName = ""
    var lastName = ""
    var telephoneNo = ""

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onNextClick() {
        when(val result = validateUseCase(firstName, lastName, telephoneNo)) {
            is ValidatePersonalInfo.Result.Success -> {
                preferences.saveFirstName(result.firstName)
                preferences.saveLastName(result.lastName)
                preferences.saveTelephoneNo(result.telephoneNo)
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.Success)
                }
            }
            is ValidatePersonalInfo.Result.Error -> {
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.ShowSnackbar(result.message))
                }

            }
        }
    }

}