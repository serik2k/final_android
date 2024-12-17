package com.example.finalandroid.data.repository

import com.example.finalandroid.data.api.WeatherApi

class WeatherRepository(private val api: WeatherApi) {
    suspend fun getWeather(latitude: String, longitude: String) = api.getWeather(latitude, longitude)
}
