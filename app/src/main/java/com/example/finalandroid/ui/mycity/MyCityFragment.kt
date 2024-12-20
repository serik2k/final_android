package com.example.finalandroid.ui.mycity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.finalandroid.R
import com.example.finalandroid.data.api.RetrofitInstance
import com.example.finalandroid.data.db.Cities
import com.example.finalandroid.data.db.DatabaseProvider
import com.example.finalandroid.data.repository.WeatherRepository
import com.example.finalandroid.ui.weather.WeatherViewModel
import com.example.finalandroid.utils.PreferencesHelper

class MyCityFragment : Fragment() {
    private lateinit var viewModel: WeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_my_city, container, false)

        val temperatureTextView = view.findViewById<TextView>(R.id.temperature)
        val windSpeedTextView = view.findViewById<TextView>(R.id.windSpeed)
        val windPowerTextView = view.findViewById<TextView>(R.id.windPower)
        val isDayTextView = view.findViewById<TextView>(R.id.isDay)
        val backgroundImage = view.findViewById<ImageView>(R.id.backgroundImage)
        val cityNameTextView = view.findViewById<TextView>(R.id.cityNameTextView)

        val selectedCityName = PreferencesHelper.getSelectedCity(requireContext())
        if (selectedCityName == null) {
            temperatureTextView.text = "Please select a city in settings."
            return view
        }

        cityNameTextView.text = selectedCityName

        val selectedCity = Cities.getCities().find { it.name == selectedCityName }
        if (selectedCity == null) {
            temperatureTextView.text = "City not found."
            return view
        }

        val database = DatabaseProvider.getDatabase(requireContext())
        val repository = WeatherRepository(RetrofitInstance.api, database)
        viewModel = WeatherViewModel(repository)

        // Fetch weather for the selected city
        viewModel.fetchWeather(selectedCity.latitude.toString(), selectedCity.longitude.toString())
            .observe(viewLifecycleOwner) { weather ->
                val temp: Double = weather.current.temperature
                temperatureTextView.text = "Temperature: $temp°C"
                windSpeedTextView.text = "Wind Speed: ${weather.current.windSpeed} km/h"
                isDayTextView.text = if (weather.current.isDay == 1) "Daytime" else "Nighttime"

                // Update wind power
                val windSpeed = weather.current.windSpeed
                val windPower = when {
                    windSpeed < 5 -> "Weak"
                    (5 <= windSpeed && windSpeed <= 15) -> "Moderate"
                    else -> "Strong"
                }
                windPowerTextView.text = "Wind Power: $windPower"

                // Update the background image based on temperature
                when {
                    temp >= 15 -> backgroundImage.setImageResource(R.drawable.hot)
                    (0 <= temp && temp <= 14) -> backgroundImage.setImageResource(R.drawable.warm)
                    else -> backgroundImage.setImageResource(R.drawable.cold)
                }
            }


        return view
    }
}

