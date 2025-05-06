package com.example.weatheapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.SearchView

class CityListFragment : Fragment() {

    private lateinit var cities: List<City>
    private lateinit var adapter: CityAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_city_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        cities = listOf(
            City("Moscow", "Russia"),
            City("New York", "USA"),
            City("Paris", "France"),
            City("Tokyo", "Japan"),
            City("Berlin", "Germany"),
            City("London", "UK")
        )

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewCities)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = CityAdapter(cities) { city ->
            val fragment = WeatherDetailsFragment.newInstance(city.name)
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
        }
        recyclerView.adapter = adapter

        val searchView = view.findViewById<SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                val filteredList = if (!newText.isNullOrEmpty()) {
                    cities.filter { it.name.contains(newText, ignoreCase = true) }
                } else {
                    cities
                }
                adapter.updateList(filteredList)
                return true
            }
        })
    }
}
