package com.example.onboardingnav.feature_onboarding.domain.use_cases

import com.example.onboardingnav.core.util.UiText
import com.example.onboardingnav.R

class ConfirmPin {
    operator fun invoke(
        pin: String?,
        confirmedPin: String
    ): ValidatePin.PinResult {
        return if (pin == confirmedPin) {
            ValidatePin.PinResult.Success(confirmedPin)
        } else {
            ValidatePin.PinResult.Error(message = UiText.StringResource(R.string.error_confirm_pin))
        }
    }
}
