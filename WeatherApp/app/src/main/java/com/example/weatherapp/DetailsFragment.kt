package com.example.weatherapp

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection
import android.widget.TextView

class DetailsFragment : Fragment(R.layout.fragment_details) {
    private lateinit var weatherBundle: Weather

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        weatherBundle = arguments?.getParcelable("BUNDLE_NAME") ?: Weather(City("?", 0.0, 0.0))
        loadWeather(view)
    }

    private fun loadWeather(view: View) {
        Thread {
            val url = URL("https://api.weather.yandex.ru/v2/informers?lat=${weatherBundle.city.lat}&lon=${weatherBundle.city.lon}")
            val connection = url.openConnection() as HttpsURLConnection
            connection.requestMethod = "GET"
            connection.setRequestProperty("X-Yandex-API-Key", "ТВОЙ_API_КЛЮЧ")

            val reader = BufferedReader(InputStreamReader(connection.inputStream))
            val json = reader.lines().collect(Collectors.joining("\n"))
            val weatherDTO = Gson().fromJson(json, WeatherDTO::class.java)
            connection.disconnect()

            requireActivity().runOnUiThread {
                view.findViewById<TextView>(R.id.cityName).text = weatherBundle.city.city
                view.findViewById<TextView>(R.id.temperatureValue).text = "${weatherDTO.fact?.temp ?: "--"}°C"
                view.findViewById<TextView>(R.id.conditionValue).text = weatherDTO.fact?.condition ?: "нет данных"
                view.findViewById<TextView>(R.id.feelsLikeValue).text = "${weatherDTO.fact?.feels_like ?: "--"}°C"
            }
        }.start()
    }
}
