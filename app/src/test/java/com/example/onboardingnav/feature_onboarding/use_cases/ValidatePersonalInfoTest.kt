package com.example.onboardingnav.feature_onboarding.use_cases

import com.example.onboardingnav.feature_onboarding.domain.use_cases.ValidatePersonalInfo
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ValidatePersonalInfoTest {

    private var validatePersonalInfoUseCase = ValidatePersonalInfo()

    @Test
    fun `Pass non-empty values, result success`() {
        val firstName = "Joe"
        val lastName = "Bloggs"
        val phoneNo = "07777777777"

        val result = validatePersonalInfoUseCase.invoke(firstName, lastName, phoneNo)

        assertThat(result).isEqualTo(ValidatePersonalInfo.Result.Success(firstName, lastName, phoneNo))
    }

    @Test
    fun `Pass empty first name, result error`() {
        val firstName = ""
        val lastName = "Bloggs"
        val phoneNo = "07777777777"

        val result = validatePersonalInfoUseCase.invoke(firstName, lastName, phoneNo)

        assertThat(result).isInstanceOf(ValidatePersonalInfo.Result.Error::class.java)
    }

    @Test
    fun `Pass empty last name, result error`() {
        val firstName = "Joe"
        val lastName = ""
        val phoneNo = "07777777777"

        val result = validatePersonalInfoUseCase.invoke(firstName, lastName, phoneNo)

        assertThat(result).isInstanceOf(ValidatePersonalInfo.Result.Error::class.java)
    }

    @Test
    fun `Pass empty phone number, result error`() {
        val firstName = "Joe"
        val lastName = "bloggs"
        val phoneNo = ""

        val result = validatePersonalInfoUseCase.invoke(firstName, lastName, phoneNo)

        assertThat(result).isInstanceOf(ValidatePersonalInfo.Result.Error::class.java)
    }

    @Test
    fun `Pass all empty values, result error`() {
        val firstName = ""
        val lastName = ""
        val phoneNo = ""

        val result = validatePersonalInfoUseCase.invoke(firstName, lastName, phoneNo)

        assertThat(result).isInstanceOf(ValidatePersonalInfo.Result.Error::class.java)
    }
}