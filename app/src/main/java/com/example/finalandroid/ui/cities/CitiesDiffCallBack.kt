package com.example.finalandroid.ui.cities

import androidx.recyclerview.widget.DiffUtil
import com.example.finalandroid.data.api.WeatherResponse

class CitiesDiffCallback : DiffUtil.ItemCallback<WeatherResponse>() {
    override fun areItemsTheSame(oldItem: WeatherResponse, newItem: WeatherResponse): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: WeatherResponse, newItem: WeatherResponse): Boolean {
        return oldItem == newItem
    }
}
