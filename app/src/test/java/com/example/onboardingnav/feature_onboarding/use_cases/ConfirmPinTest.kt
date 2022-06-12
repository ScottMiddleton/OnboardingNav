package com.example.onboardingnav.feature_onboarding.use_cases

import com.example.onboardingnav.feature_onboarding.domain.use_cases.ConfirmPin
import com.example.onboardingnav.feature_onboarding.domain.use_cases.ValidatePin
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ConfirmPinTest {

    private var confirmPinUseCase = ConfirmPin()

    @Test
    fun `Pass matching pins, result success`() {
        val pin = "1234"
        val confirmedPin = "1234"
        val result = confirmPinUseCase.invoke(pin, confirmedPin)

        assertThat(result).isEqualTo(ValidatePin.PinResult.Success(confirmedPin))
    }

    @Test
    fun `Pass different pin, result error`() {
        val pin = "1111"
        val confirmedPin = "2222"
        val result = confirmPinUseCase.invoke(pin, confirmedPin)

        assertThat(result).isInstanceOf(ValidatePin.PinResult.Error::class.java)
    }

    @Test
    fun `Pass one empty pin, result error`() {
        val pin = "1111"
        val confirmedPin = ""
        val result = confirmPinUseCase.invoke(pin, confirmedPin)

        assertThat(result).isInstanceOf(ValidatePin.PinResult.Error::class.java)
    }

    @Test
    fun `Pass null pin, result error`() {
        val confirmedPin = "1234"
        val result = confirmPinUseCase.invoke(null, confirmedPin)

        assertThat(result).isInstanceOf(ValidatePin.PinResult.Error::class.java)
    }
}