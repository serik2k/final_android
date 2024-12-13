package com.example.finalandroid.ui.settings


import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.internal.NopCollector.emit

class SettingsViewModel : ViewModel() {
    fun getSettings() = liveData(Dispatchers.IO) {
        // Simulate fetching settings (replace with actual logic if needed)
        emit("Example Settings")
    }
}