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

        // Set an empty adapter initially
        adapter = WeatherAdapter()
        recyclerView.adapter = adapter

        Log.d("WeatherFragment", "RecyclerView setup complete")

        val database = DatabaseProvider.getDatabase(requireContext());

        // Initialize the repository and ViewModel
        val repository = WeatherRepository(RetrofitInstance.api, database)
        val factory = WeatherViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(WeatherViewModel::class.java)

        // Fetch the data
        fetchWeatherForCities()

        return view
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

                    // Add to the list and submit the new list to the adapter
                    cityWeatherList.add(cityWeather)
                    adapter.submitList(cityWeatherList.toList()) // Submit a copy of the list
                }
        }
    }

//    private fun fetchWeatherForCities() {
//        val cityWeatherList = mutableListOf<CityWeather>()
//        val cities = Cities.getCities()
//
//        cities.forEach { city ->
//            viewModel.fetchWeather(city.latitude.toString(), city.longitude.toString())
//                .observe(viewLifecycleOwner) { weatherResponse ->
//                    val cityWeather = CityWeather(
//                        cityName = city.name,
//                        temperature = "${weatherResponse.current.temperature}°C",
//                        latitude = city.latitude,
//                        longitude = city.longitude
//                    )
//
//                    cityWeatherList.add(cityWeather)
//
//                    // Update adapter once all responses are fetched
//                    if (cityWeatherList.size == cities.size) {
//                        if (cityWeatherList.isNotEmpty()) {
//                            adapter.updateWeather(cityWeatherList)
//                        } else {
//                            Log.e("WeatherFragment", "No data to update the adapter")
//                        }
//
//                        Log.d("WeatherFragment", "All cities' weather fetched: $cityWeatherList")
//                    }
//                }
//        }
//    }
}
