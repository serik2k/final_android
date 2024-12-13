package com.example.finalandroid.data.repository

import com.example.weatherapp.data.api.WeatherApi
import com.example.weatherapp.data.db.CityDao

class WeatherRepository(
    private val api: WeatherApi,
    private val dao: CityDao
) {
    suspend fun getWeather(city: String, apiKey: String) = api.getWeather(city, apiKey)

    suspend fun saveCity(city: String) = dao.insertCity(FavoriteCity(city))

    suspend fun getFavoriteCities() = dao.getAllCities()
}