package com.example.finalandroid.ui.cities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.finalandroid.data.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers

class CitiesViewModel(private val repository: WeatherRepository) : ViewModel() {
    fun getWeatherForCity(city: String, apiKey: String) = liveData(Dispatchers.IO) {
        emit(repository.getWeather(city, apiKey))
    }
}