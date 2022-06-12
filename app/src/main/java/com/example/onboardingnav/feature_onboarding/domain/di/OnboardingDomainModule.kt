package com.example.onboardingnav.feature_onboarding.domain.di

import com.example.onboardingnav.feature_onboarding.domain.use_cases.ConfirmPin
import com.example.onboardingnav.feature_onboarding.domain.use_cases.ValidateCredentials
import com.example.onboardingnav.feature_onboarding.domain.use_cases.ValidatePersonalInfo
import com.example.onboardingnav.feature_onboarding.domain.use_cases.ValidatePin
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object OnboardingDomainModule {

    @Provides
    @ViewModelScoped
    fun provideValidateCredentialsUseCase(): ValidateCredentials {
        return ValidateCredentials()
    }

    @Provides
    @ViewModelScoped
    fun provideValidatePersonalInfoUseCase(): ValidatePersonalInfo {
        return ValidatePersonalInfo()
    }

    @Provides
    @ViewModelScoped
    fun provideValidatePinUseCase(): ValidatePin {
        return ValidatePin()
    }

    @Provides
    @ViewModelScoped
    fun provideConfirmPinUseCase(): ConfirmPin {
        return ConfirmPin()
    }
}