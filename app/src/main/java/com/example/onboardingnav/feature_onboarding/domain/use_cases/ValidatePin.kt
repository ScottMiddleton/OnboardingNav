package com.example.onboardingnav.feature_onboarding.domain.use_cases

import com.example.onboardingnav.core.util.Constants.REQUIRED_PIN_LENGTH
import com.example.onboardingnav.core.util.UiText
import com.example.onboardingnav.R

class ValidatePin {
    operator fun invoke(
        pin: String
    ): PinResult {
        return if (pin.length == REQUIRED_PIN_LENGTH) {
            PinResult.Success(pin)
        } else {
            PinResult.Error(message = UiText.StringResource(R.string.error_pin_length))
        }
    }

    sealed class PinResult {
        data class Success(
            val pin: String
        ) : PinResult()

        data class Error(val message: UiText) : PinResult()
    }
}
