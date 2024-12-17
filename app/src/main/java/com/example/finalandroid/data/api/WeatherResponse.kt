package com.example.finalandroid.data.api

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("current") val current: CurrentWeather,
    @SerializedName("hourly") val hourly: HourlyWeather
)

data class CurrentWeather(
    @SerializedName("temperature_2m") val temperature: Double,
    @SerializedName("is_day") val isDay: Int,
    @SerializedName("rain") val rain: Double?,
    @SerializedName("wind_speed_10m") val windSpeed: Double
)

data class HourlyWeather(
    @SerializedName("temperature_2m") val temperatureList: List<Double>
)
