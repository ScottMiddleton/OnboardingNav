package com.example.onboardingnav.core.domain.preferences

import com.example.onboardingnav.feature_home.domain.model.UserName

interface Preferences {
    fun saveEmail(email: String)
    fun savePassword(password: String)
    fun saveFirstName(firstName: String)
    fun saveLastName(lastName: String)
    fun saveTelephoneNo(telephoneNo: String)
    fun savePin(pin: String)
    fun clearAll()

    fun loadUserName(): UserName

    fun saveShouldShowOnboarding(shouldShow: Boolean)
    fun loadShouldShowOnboarding(): Boolean

    companion object {
        const val KEY_EMAIL = "email"
        const val KEY_PASSWORD = "password"
        const val KEY_FIRST_NAME = "first_name"
        const val KEY_LAST_NAME = "last_name"
        const val KEY_SAVE_TELEPHONE_NO = "telephone"
        const val KEY_PIN = "pin"
        const val KEY_SHOULD_SHOW_ONBOARDING = "should_show_onboarding"
    }
}