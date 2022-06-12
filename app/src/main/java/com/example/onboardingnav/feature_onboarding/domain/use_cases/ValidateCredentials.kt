package com.example.onboardingnav.feature_onboarding.domain.use_cases

import com.example.onboardingnav.core.util.UiText
import com.example.onboardingnav.R

class ValidateCredentials {
    operator fun invoke(
        email: String,
        password: String,
    ): Result {
        if(email.isEmpty() || password.isEmpty()) {
            return Result.Error(
                message = UiText.StringResource(R.string.error_all_fields_required_to_progress)
            )
        }
        return Result.Success(
            email, password
        )
    }

    sealed class Result {
        data class Success(
            val email: String,
            val password: String
        ): Result()
        data class Error(val message: UiText): Result()
    }
}