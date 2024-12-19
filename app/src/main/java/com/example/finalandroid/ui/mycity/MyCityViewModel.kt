package com.example.finalandroid.ui.mycity

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.finalandroid.data.api.WeatherApi
import com.example.finalandroid.data.db.DatabaseProvider
import com.example.finalandroid.data.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers

class MyCityViewModel(context: Context, api: WeatherApi) : ViewModel() {
    private val database = DatabaseProvider.getDatabase(context)
    val repository = WeatherRepository(api, database)

    fun getUserCityWeather(apiKey: String) = liveData(Dispatchers.IO) {
        // Replace "YourCity" with the user's actual city logic
        emit(repository.getWeather("YourCity", apiKey))
    }
}

class MyCityViewModelFactory(
    private val context: Context,
    private val api: WeatherApi
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyCityViewModel::class.java)) {
            return MyCityViewModel(context, api) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}