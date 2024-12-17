package com.example.finalandroid.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalandroid.R
import androidx.fragment.app.Fragment
import com.example.finalandroid.data.api.RetrofitInstance
import com.example.finalandroid.data.repository.WeatherRepository

class WeatherFragment : Fragment() {
    private lateinit var viewModel: WeatherViewModel
    private lateinit var adapter: WeatherAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_weather, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = WeatherAdapter()
        recyclerView.adapter = adapter

        val repository = WeatherRepository(RetrofitInstance.api)
        viewModel = WeatherViewModel(repository)

        // Replace with actual latitude and longitude values
        viewModel.fetchWeather("52.52", "13.41").observe(viewLifecycleOwner) { weather ->
            adapter.updateWeather(weather)
        }

        return view
    }
}
