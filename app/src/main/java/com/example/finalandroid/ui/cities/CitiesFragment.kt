package com.example.finalandroid.ui.cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalandroid.databinding.FragmentCitiesBinding

class CitiesFragment : Fragment() {
    private var _binding: FragmentCitiesBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CitiesViewModel

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
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Example: Load weather for a city
        viewModel.getWeatherForCity("London", "YOUR_API_KEY_HERE").observe(viewLifecycleOwner) { weather ->
            // Update RecyclerView with weather data
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
