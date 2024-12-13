package com.example.finalandroid.ui.mycity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.finalandroid.data.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.internal.NopCollector.emit

class MyCityViewModel(private val repository: WeatherRepository) : ViewModel() {
    fun getUserCityWeather(apiKey: String) = liveData(Dispatchers.IO) {
        // Replace "YourCity" with the user's actual city logic
        emit(repository.getWeather("YourCity", apiKey))
    }
}
