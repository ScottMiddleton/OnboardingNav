package com.example.onboardingnav.core.data

import android.content.SharedPreferences
import com.example.onboardingnav.feature_home.domain.model.UserInfo
import com.example.onboardingnav.core.domain.preferences.Preferences

class DefaultPreferences(
    private val sharedPref: SharedPreferences
): Preferences {
    override fun saveEmail(email: String) {
        sharedPref.edit()
            .putString(Preferences.KEY_EMAIL, email)
            .apply()
    }

    override fun savePassword(password: String) {
        sharedPref.edit()
            .putString(Preferences.KEY_PASSWORD, password)
            .apply()
    }

    override fun saveFirstName(firstName: String) {
        sharedPref.edit()
            .putString(Preferences.KEY_FIRST_NAME, firstName)
            .apply()
    }

    override fun saveLastName(lastName: String) {
        sharedPref.edit()
            .putString(Preferences.KEY_LAST_NAME, lastName)
            .apply()
    }

    override fun saveTelephoneNo(telephoneNo: String) {
        sharedPref.edit()
            .putString(Preferences.KEY_TELEPHONE_NO, telephoneNo)
            .apply()
    }

    override fun savePin(pin: String) {
        sharedPref.edit()
            .putString(Preferences.KEY_PIN, pin)
            .apply()
    }

    override fun loadUserInfo(): UserInfo {
        val firstName = sharedPref.getString(Preferences.KEY_FIRST_NAME, null)
        val lastName = sharedPref.getString(Preferences.KEY_LAST_NAME, null)
        val email = sharedPref.getString(Preferences.KEY_EMAIL, null)
        val phone = sharedPref.getString(Preferences.KEY_TELEPHONE_NO, null)

        return UserInfo(
            firstName = firstName ?: "",
            lastName = lastName ?: "",
            email = email ?: "",
            phoneNo = phone ?: ""
        )
    }

    override fun clearAll() {
        sharedPref.edit().clear().apply()
    }

    override fun saveShouldShowOnboarding(shouldShow: Boolean) {
        sharedPref.edit()
            .putBoolean(Preferences.KEY_SHOULD_SHOW_ONBOARDING, shouldShow)
            .apply()
    }

    override fun loadShouldShowOnboarding(): Boolean {
        return sharedPref.getBoolean(
            Preferences.KEY_SHOULD_SHOW_ONBOARDING,
            true
        )
    }
}