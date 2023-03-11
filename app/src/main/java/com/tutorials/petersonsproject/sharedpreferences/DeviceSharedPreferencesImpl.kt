package com.tutorials.petersonsproject.sharedpreferences

import android.content.Context
import android.content.SharedPreferences

class DeviceSharedPreferencesImpl(context: Context) : DeviceSharedPreferences {
    companion object {
        private const val SHARED_PREFS_FILE_NAME = "device_preferences"
        private const val ACCESS_TOKEN = "access_token"
    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_FILE_NAME, Context.MODE_PRIVATE)

    override fun getAccessToken(): String {
        return sharedPreferences.getString(ACCESS_TOKEN, "") ?: ""
    }


    override fun setAccessToken(token: String) {
        sharedPreferences.edit().putString(ACCESS_TOKEN, "Bearer $token").apply()
    }


}