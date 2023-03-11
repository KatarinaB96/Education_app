package com.tutorials.petersonsproject.sharedpreferences

interface DeviceSharedPreferences {
    fun getAccessToken(): String
    fun setAccessToken(token:String)
}