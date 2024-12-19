package com.example.finalandroid.ui.weather

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalandroid.data.api.WeatherResponse
import com.example.finalandroid.data.repository.WeatherRepository
import kotlinx.coroutines.launch

class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {
    private val _weather = MutableLiveData<WeatherResponse>()
    val weather: LiveData<WeatherResponse> get() = _weather

    fun fetchWeather(latitude: String, longitude: String): LiveData<WeatherResponse> {
        val result = MutableLiveData<WeatherResponse>()
        viewModelScope.launch {
            try {
                val response = repository.getWeather(latitude, longitude)
                if (response != null) {
                    result.postValue(response)
                    Log.d("WeatherViewModel", "Response received: $response")
                } else {
                    Log.d("WeatherViewModel", "Response is null")
                }
            } catch (e: Exception) {
                Log.e("WeatherViewModel", "Error fetching weather", e)
            }
        }
        return result
    }
}
