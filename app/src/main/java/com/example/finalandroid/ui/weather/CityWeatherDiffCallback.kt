package com.example.finalandroid.ui.weather

import androidx.recyclerview.widget.DiffUtil
import com.example.finalandroid.data.api.CityWeather

class CityWeatherDiffCallback : DiffUtil.ItemCallback<CityWeather>() {
    override fun areItemsTheSame(oldItem: CityWeather, newItem: CityWeather): Boolean {
        return oldItem.cityName == newItem.cityName
    }

    override fun areContentsTheSame(oldItem: CityWeather, newItem: CityWeather): Boolean {
        return oldItem == newItem
    }
}

