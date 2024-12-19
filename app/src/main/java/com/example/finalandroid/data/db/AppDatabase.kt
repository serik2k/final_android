package com.example.finalandroid.data.db

import androidx.room.*
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city_weather")
data class CityWeather(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val cityName: String,
    val temperature: String,
    val latitude: Double,
    val longitude: Double
)

@Entity(tableName = "favorite_cities")
data class FavoriteCity(
    @PrimaryKey val name: String
)
