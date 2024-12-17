package com.example.finalandroid.ui.cities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.finalandroid.ui.cities.CitiesAdapter.CitiesDiffCallback
import com.bumptech.glide.Glide
import com.example.finalandroid.R
import com.example.finalandroid.data.api.WeatherResponse
import kotlinx.android.synthetic.main.item_city.view.*

class CitiesAdapter : ListAdapter<WeatherResponse, CitiesAdapter.CityViewHolder>(CitiesDiffCallback()) {

    class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(weather: WeatherResponse) {
            val cityNameView = itemView.findViewById<TextView>(R.id.cityName)
            val temperatureView = itemView.findViewById<TextView>(R.id.temperature)
            val descriptionView = itemView.findViewById<TextView>(R.id.description)
            val weatherIconView = itemView.findViewById<ImageView>(R.id.weatherIcon)

            cityNameView.text = weather.name
            temperatureView.text = "Temp: ${weather.main.temp}K"
            descriptionView.text = weather.weather.firstOrNull()?.description ?: "N/A"

            Glide.with(itemView.context)
                .load("https://openweathermap.org/img/wn/${weather.weather.firstOrNull()?.icon}@2x.png")
                .into(weatherIconView)
        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false)
            return CityViewHolder(view)
        }

        override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
            holder.bind(getItem(position))
        }
    }

    class CitiesDiffCallback : DiffUtil.ItemCallback<WeatherResponse>() {
        override fun areItemsTheSame(oldItem: WeatherResponse, newItem: WeatherResponse): Boolean {
            return oldItem.cityName == newItem.cityName
        }

        override fun areContentsTheSame(
            oldItem: WeatherResponse,
            newItem: WeatherResponse
        ): Boolean {
            return oldItem == newItem
        }
    }
}
