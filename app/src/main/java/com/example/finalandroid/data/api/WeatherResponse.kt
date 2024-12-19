package com.example.finalandroid.data.api

import androidx.room.Entity
import androidx.room.PrimaryKey
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

@Entity
data class CityWeather(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val cityName: String,
    val temperature: String,
    val latitude: Double,
    val longitude: Double
)

