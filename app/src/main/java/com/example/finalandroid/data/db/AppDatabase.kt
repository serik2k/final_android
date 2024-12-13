package com.example.finalandroid.data.db


import androidx.room.*


@Database(entities = [FavoriteCity::class], version = 1)
    abstract class AppDatabase : RoomDatabase() {
        abstract fun cityDao(): CityDao
    }
