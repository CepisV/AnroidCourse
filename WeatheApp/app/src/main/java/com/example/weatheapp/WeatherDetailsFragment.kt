package com.example.weatheapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatheapp.model.AppState

class WeatherDetailsFragment : Fragment() {

    private lateinit var detailsViewModel: DetailsViewModel
    private var cityName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cityName = arguments?.getString(ARG_CITY_NAME)

        detailsViewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)

        detailsViewModel.detailsLiveData.observe(this, Observer { appState ->
            renderData(appState)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        cityName?.let {
            val lat = 52.52
            val lon = 13.405
            detailsViewModel.getWeatherFromRemoteSource(lat, lon)
        }
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Loading -> {
                view?.findViewById<TextView>(R.id.textTemperature)?.text = "Loading..."
            }
            is AppState.Success -> {
                val weather = appState.weatherData
                view?.findViewById<TextView>(R.id.textTemperature)?.text =
                    "${weather.main.temp} °C"
                view?.findViewById<TextView>(R.id.textDescription)?.text =
                    weather.weather.firstOrNull()?.description ?: "No description"
            }
            is AppState.Error -> {
                Toast.makeText(requireContext(), "Error: ${appState.error.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        private const val ARG_CITY_NAME = "city_name"

        fun newInstance(cityName: String): WeatherDetailsFragment {
            val fragment = WeatherDetailsFragment()
            val args = Bundle()
            args.putString(ARG_CITY_NAME, cityName)
            fragment.arguments = args
            return fragment
        }
    }
}
