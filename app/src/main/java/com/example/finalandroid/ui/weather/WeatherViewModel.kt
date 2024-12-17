package com.example.finalandroid.ui.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.finalandroid.data.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers

class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {
    fun fetchWeather(latitude: String, longitude: String) = liveData(Dispatchers.IO) {
        emit(repository.getWeather(latitude, longitude))
    }
}
