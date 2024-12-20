package com.example.finalandroid.ui.mycity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        val temperature = view.findViewById<TextView>(R.id.temperature)
        val windSpeed = view.findViewById<TextView>(R.id.windSpeed)
        val isDay = view.findViewById<TextView>(R.id.isDay)

        val selectedCityName = PreferencesHelper.getSelectedCity(requireContext())
        if (selectedCityName == null) {
            temperature.text = "Please select a city in settings."
            return view
        }

        val selectedCity = Cities.getCities().find { it.name == selectedCityName }
        if (selectedCity == null) {
            temperature.text = "City not found."
            return view
        }

        val database = DatabaseProvider.getDatabase(requireContext())
        val repository = WeatherRepository(RetrofitInstance.api, database)
        viewModel = WeatherViewModel(repository)

        // Fetch weather for the selected city
        viewModel.fetchWeather(selectedCity.latitude.toString(), selectedCity.longitude.toString())
            .observe(viewLifecycleOwner) { weather ->
                temperature.text = "Temperature: ${weather.current.temperature}°C"
                windSpeed.text = "Wind Speed: ${weather.current.windSpeed} km/h"
                isDay.text = if (weather.current.isDay == 1) "Daytime" else "Nighttime"
            }

        return view
    }
}
