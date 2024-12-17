package com.example.finalandroid.ui.mycity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.finalandroid.R
import com.example.finalandroid.data.api.RetrofitInstance
import com.example.finalandroid.data.repository.WeatherRepository
import com.example.finalandroid.ui.weather.WeatherViewModel

class MyCityFragment : Fragment() {
    private lateinit var viewModel: WeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_my_city, container, false)

        val temperature = view.findViewById<TextView>(R.id.temperature)
        val windSpeed = view.findViewById<TextView>(R.id.windSpeed)
        val isDay = view.findViewById<TextView>(R.id.isDay)

        val repository = WeatherRepository(RetrofitInstance.api)
        viewModel = WeatherViewModel(repository)

        // Replace with your desired coordinates
        viewModel.fetchWeather("52.52", "13.41").observe(viewLifecycleOwner) { weather ->
            temperature.text = "Temperature: ${weather.current.temperature}°C"
            windSpeed.text = "Wind Speed: ${weather.current.windSpeed} km/h"
            isDay.text = if (weather.current.isDay == 1) "Daytime" else "Nighttime"
        }

        return view
    }
}
