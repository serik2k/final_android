package com.example.finalandroid.ui.weather

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.finalandroid.R
import com.example.finalandroid.data.api.CityWeather
import com.example.finalandroid.data.api.WeatherResponse

class WeatherAdapter : ListAdapter<CityWeather, WeatherAdapter.WeatherViewHolder>(CityWeatherDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)
        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(cityWeather: CityWeather) {
            itemView.findViewById<TextView>(R.id.cityName).text = cityWeather.cityName
            itemView.findViewById<TextView>(R.id.temperature).text = cityWeather.temperature
        }
    }
}


