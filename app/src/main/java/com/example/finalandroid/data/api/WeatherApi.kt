package com.example.finalandroid.data.api


import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("v1/forecast")
    suspend fun getWeather(
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String,
        @Query("current") current: String = "temperature_2m,is_day,rain,wind_speed_10m",
        @Query("hourly") hourly: String = "temperature_2m",
        @Query("timezone") timezone: String = "GMT",
        @Query("forecast_hours") forecastHours: Int = 12
    ): WeatherResponse
}
