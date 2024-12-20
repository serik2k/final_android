package com.example.finalandroid.ui.weather

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalandroid.R
import androidx.fragment.app.Fragment
import com.example.finalandroid.data.api.CityWeather
import com.example.finalandroid.data.api.RetrofitInstance
import com.example.finalandroid.data.db.Cities
import com.example.finalandroid.data.repository.WeatherRepository
import androidx.lifecycle.ViewModelProvider
import com.example.finalandroid.data.db.DatabaseProvider

class WeatherFragment : Fragment() {
    private lateinit var viewModel: WeatherViewModel
    private lateinit var adapter: WeatherAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_weather, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = WeatherAdapter()
        recyclerView.adapter = adapter

        val database = DatabaseProvider.getDatabase(requireContext())

        // Initialize the repository and ViewModel
        val repository = WeatherRepository(RetrofitInstance.api, database)
        val factory = WeatherViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(WeatherViewModel::class.java)

        // **Fetch local weather first**
        fetchLocalWeather()

        // **Fetch new data from API**
        fetchWeatherForCities()

        return view
    }

    private fun fetchLocalWeather() {
        viewModel.getLocalWeather().observe(viewLifecycleOwner) { localWeather ->
            if (localWeather.isNotEmpty()) {
                Log.d("WeatherFragment", "Loaded weather from database: $localWeather")
                adapter.submitList(localWeather)
            } else {
                Log.d("WeatherFragment", "No local weather data found")
            }
        }
    }


    private fun fetchWeatherForCities() {
        val cities = Cities.getCities()
        val cityWeatherList = mutableListOf<CityWeather>()

        cities.forEach { city ->
            viewModel.fetchWeather(city.latitude.toString(), city.longitude.toString())
                .observe(viewLifecycleOwner) { weatherResponse ->
                    val cityWeather = CityWeather(
                        cityName = city.name,
                        temperature = "${weatherResponse.current.temperature}°C",
                        latitude = city.latitude,
                        longitude = city.longitude
                    )

                    // Add to the list and submit it
                    cityWeatherList.add(cityWeather)
                    adapter.submitList(cityWeatherList.toList()) // Submit a copy of the list

                    // Save to local database
                    if (cityWeatherList.size == cities.size) {
                        Log.d("WeatherFragment", "Saving data to database: $cityWeatherList")
                        viewModel.saveWeather(cityWeatherList)
                    }
                }
        }
    }

}
