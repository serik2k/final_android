package com.example.finalandroid.ui.weather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finalandroid.R
import com.example.finalandroid.data.api.WeatherResponse

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {
    private val weatherList = mutableListOf<WeatherResponse>()

    fun updateWeather(data: WeatherResponse) {
        weatherList.clear()
        weatherList.add(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)
        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val weather = weatherList[position]
        holder.bind(weather)
    }

    override fun getItemCount() = weatherList.size

    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(weather: WeatherResponse) {
            itemView.findViewById<TextView>(R.id.temperature).text = "Temp: ${weather.current.temperature}°C"
            itemView.findViewById<TextView>(R.id.windSpeed).text = "Wind: ${weather.current.windSpeed} km/h"
            itemView.findViewById<TextView>(R.id.isDay).text =
                if (weather.current.isDay == 1) "Daytime" else "Nighttime"
        }
    }
}
