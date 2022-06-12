package com.example.onboardingnav.feature_onboarding.domain.use_cases

import com.example.onboardingnav.core.util.UiText
import com.example.onboardingnav.R

class ValidatePersonalInfo {
    operator fun invoke(
        firstName: String,
        lastName: String,
        telephoneNo: String

    ): Result {
        if(firstName.isEmpty() || lastName.isEmpty() || telephoneNo.isEmpty() ) {
            return Result.Error(
                message = UiText.StringResource(R.string.error_all_fields_required_to_progress)
            )
        }
        return Result.Success(
            firstName, lastName, telephoneNo
        )
    }

    sealed class Result {
        data class Success(
            val firstName: String,
            val lastName: String,
            val telephoneNo: String
        ): Result()
        data class Error(val message: UiText): Result()
    }
}