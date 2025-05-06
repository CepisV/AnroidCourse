package com.example.weatherapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainFragment : Fragment(R.layout.fragment_main) {

    private val weatherList = listOf(
        Weather(City("Москва", 55.75, 37.62)),
        Weather(City("Санкт-Петербург", 59.93, 30.31)),
        Weather(City("Новосибирск", 55.03, 82.92))
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = WeatherAdapter(weatherList) { weather ->
            val bundle = Bundle().apply {
                putParcelable("BUNDLE_NAME", weather)
            }
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, DetailsFragment::class.java, bundle)
                .addToBackStack(null)
                .commit()
        }
    }
}
