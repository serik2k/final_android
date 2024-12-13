package com.example.finalandroid.data.api
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi{
@GET("weather")
suspend fun getWeather(
    @Query("q") city: String,
    @Query("appid") apiKey: String
): WeatherResponse