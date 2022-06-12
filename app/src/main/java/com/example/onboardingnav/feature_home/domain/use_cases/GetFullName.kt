package com.example.onboardingnav.feature_home.domain.use_cases

class GetFullName() {
    operator fun invoke(firstName: String, lastName: String): String {
        return "$firstName $lastName"
    }
}