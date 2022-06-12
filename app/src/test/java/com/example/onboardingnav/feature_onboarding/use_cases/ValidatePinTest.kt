package com.example.onboardingnav.feature_onboarding.use_cases

import com.example.onboardingnav.feature_onboarding.domain.use_cases.ValidatePin
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ValidatePinTest {

    private var validatePinUseCase = ValidatePin()

    @Test
    fun `Pass 4-digit pin, result success`() {
        val pin = "1234"
        val result = validatePinUseCase.invoke(pin)

        assertThat(result).isEqualTo(ValidatePin.PinResult.Success(pin))
    }

    @Test
    fun `Pass long pin, result error`() {
        val pin = "11111"
        val result = validatePinUseCase.invoke(pin)

        assertThat(result).isInstanceOf(ValidatePin.PinResult.Error::class.java)
    }

    @Test
    fun `Pass short pin, result error`() {
        val pin = "111"
        val result = validatePinUseCase.invoke(pin)

        assertThat(result).isInstanceOf(ValidatePin.PinResult.Error::class.java)
    }

    @Test
    fun `Pass empty pin, result error`() {
        val pin = ""
        val result = validatePinUseCase.invoke(pin)

        assertThat(result).isInstanceOf(ValidatePin.PinResult.Error::class.java)
    }
}