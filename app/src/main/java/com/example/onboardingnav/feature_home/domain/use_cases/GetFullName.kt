package com.example.onboardingnav.feature_home.domain.use_cases

import com.example.onboardingnav.core.domain.preferences.Preferences

class GetFullName(private val preferences: Preferences) {
    operator fun invoke(): String {
        val username = preferences.loadUserName()
        val firstName = username.firstName
        val lastName = username.lastName

        return "$firstName $lastName"
    }
}