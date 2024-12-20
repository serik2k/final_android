package com.example.finalandroid.utils

import android.content.Context
import android.content.SharedPreferences

object PreferencesHelper {
    private const val PREF_NAME = "weather_app_prefs"
    private const val SELECTED_CITY = "selected_city"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun saveSelectedCity(context: Context, cityName: String) {
        getPreferences(context).edit().putString(SELECTED_CITY, cityName).apply()
    }

    fun getSelectedCity(context: Context): String? {
        return getPreferences(context).getString(SELECTED_CITY, null)
    }
}
