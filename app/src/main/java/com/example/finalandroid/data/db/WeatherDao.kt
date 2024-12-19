package com.example.finalandroid.data.db

import androidx.room.*

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(cityWeather: List<CityWeather>)

    @Query("SELECT * FROM city_weather")
    suspend fun getAllWeather(): List<CityWeather>
}
