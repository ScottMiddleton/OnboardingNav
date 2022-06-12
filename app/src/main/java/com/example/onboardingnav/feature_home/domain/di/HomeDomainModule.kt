package com.example.onboardingnav.feature_home.domain.di

import com.example.onboardingnav.feature_home.domain.use_cases.GetFullName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object HomeDomainModule {

    @Provides
    @ViewModelScoped
    fun provideGetFullNameUseCase(): GetFullName {
        return GetFullName()
    }
}