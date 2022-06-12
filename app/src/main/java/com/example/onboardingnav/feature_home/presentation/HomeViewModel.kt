package com.example.onboardingnav.feature_home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onboardingnav.core.domain.preferences.Preferences
import com.example.onboardingnav.core.util.UiEvent
import com.example.onboardingnav.feature_home.domain.use_cases.GetFullName
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val preferences: Preferences,
    getFullName: GetFullName,
) : ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    val userInfo = preferences.loadUserInfo()
    val userFullName = getFullName(userInfo.firstName, userInfo.lastName)

    fun onLogout() {
        preferences.clearAll()
        viewModelScope.launch {
            _uiEvent.send(UiEvent.Logout)
        }
    }
}