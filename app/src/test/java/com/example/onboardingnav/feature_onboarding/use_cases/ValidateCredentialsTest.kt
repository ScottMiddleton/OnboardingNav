package com.example.onboardingnav.feature_onboarding.use_cases

import com.example.onboardingnav.feature_onboarding.domain.use_cases.ValidateCredentials
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ValidateCredentialsTest {

    private var validateCredentialsUseCase = ValidateCredentials()

    @Test
    fun `Pass non-empty values, result success`() {
        val email = "example@email.com"
        val password = "Password123"

        val result = validateCredentialsUseCase.invoke(email, password)

        assertThat(result).isEqualTo(ValidateCredentials.Result.Success(email, password))
    }

    @Test
    fun `Pass empty email, result error`() {
        val email = ""
        val password = "Password123"

        val result = validateCredentialsUseCase.invoke(email, password)

        assertThat(result).isInstanceOf(ValidateCredentials.Result.Error::class.java)
    }

    @Test
    fun `Pass empty password, result error`() {
        val email = "example@email.com"
        val password = ""

        val result = validateCredentialsUseCase.invoke(email, password)

        assertThat(result).isInstanceOf(ValidateCredentials.Result.Error::class.java)
    }

    @Test
    fun `Pass all empty values, result error`() {
        val email = ""
        val password = ""

        val result = validateCredentialsUseCase.invoke(email, password)

        assertThat(result).isInstanceOf(ValidateCredentials.Result.Error::class.java)
    }
}