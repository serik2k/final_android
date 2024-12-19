package com.example.finalandroid.data.repository

import com.example.finalandroid.data.api.WeatherApi
import com.example.finalandroid.data.api.CityWeather
import com.example.finalandroid.data.db.AppDatabase

class WeatherRepository(private val api: WeatherApi, private val db: AppDatabase) {
    suspend fun getWeather(latitude: String, longitude: String) = api.getWeather(latitude, longitude)

    suspend fun getLocalWeather(): List<com.example.finalandroid.data.api.CityWeather> {
        return db.weatherDao().getAllWeather().map { dbCityWeatherToApiCityWeather(it) }
    }

    suspend fun saveWeather(apiCityWeatherList: List<com.example.finalandroid.data.api.CityWeather>) {
        val dbCityWeatherList = apiCityWeatherList.map { apiCityWeatherToDbCityWeather(it) }
        db.weatherDao().insertWeather(dbCityWeatherList)
    }

    private fun apiCityWeatherToDbCityWeather(apiCityWeather: com.example.finalandroid.data.api.CityWeather): com.example.finalandroid.data.db.CityWeather {
        return com.example.finalandroid.data.db.CityWeather(
            id = 0, // Assign default or unique ID
            cityName = apiCityWeather.cityName,
            temperature = apiCityWeather.temperature,
            latitude = apiCityWeather.latitude,
            longitude = apiCityWeather.longitude
        )
    }

    private fun dbCityWeatherToApiCityWeather(dbCityWeather: com.example.finalandroid.data.db.CityWeather): com.example.finalandroid.data.api.CityWeather {
        return com.example.finalandroid.data.api.CityWeather(
            cityName = dbCityWeather.cityName,
            temperature = dbCityWeather.temperature,
            latitude = dbCityWeather.latitude,
            longitude = dbCityWeather.longitude
        )
    }
}

