package com.example.finalandroid.data.db

import androidx.room.*

@Dao
interface CityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: FavoriteCity)

    @Query("SELECT * FROM favorite_cities")
    suspend fun getAllCities(): List<FavoriteCity>
}