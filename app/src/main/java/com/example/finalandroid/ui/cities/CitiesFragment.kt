package com.example.weatherapp.ui.cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.finalandroid.adapter.CitiesAdapter
import com.example.finalandroid.databinding.FragmentCitiesBinding
import com.example.finalandroid.data.api.WeatherResponse
import com.example.finalandroid.ui.cities.CitiesAdapter
import com.example.finalandroid.ui.cities.CitiesViewModel

class CitiesFragment : Fragment() {
    private var _binding: FragmentCitiesBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CitiesViewModel
    private lateinit var adapter: CitiesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCitiesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(CitiesViewModel::class.java)
        adapter = CitiesAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        viewModel.getWeatherForCity("London", "YOUR_API_KEY_HERE").observe(viewLifecycleOwner) { weather ->
            adapter.submitList(listOf(weather))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}