package com.example.finalandroid.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.finalandroid.R
import com.example.finalandroid.data.db.Cities
import com.example.finalandroid.utils.PreferencesHelper

class SettingsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        val citySpinner = view.findViewById<Spinner>(R.id.citySpinner)

        // Get city names
        val cityNames = Cities.getCities().map { it.name }

        // Set up Spinner adapter
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, cityNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        citySpinner.adapter = adapter

        // Restore the selected city
        val selectedCity = PreferencesHelper.getSelectedCity(requireContext())
        if (selectedCity != null) {
            val position = cityNames.indexOf(selectedCity)
            if (position >= 0) {
                citySpinner.setSelection(position)
            }
        }

        // Handle city selection
        citySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedCity = cityNames[position]
                PreferencesHelper.saveSelectedCity(requireContext(), selectedCity)
                Toast.makeText(requireContext(), "City Selected: $selectedCity", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        return view
    }
}
