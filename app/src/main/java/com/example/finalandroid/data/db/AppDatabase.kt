package com.example.finalandroid.data.db

import androidx.room.*

@Entity(tableName = "favorite_cities")
data class FavoriteCity(
    @PrimaryKey val name: String
)



