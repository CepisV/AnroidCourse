package com.example.myserviceapp

import android.app.IntentService
import android.content.Intent
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class WeatherService : IntentService("WeatherService") {

    override fun onHandleIntent(intent: Intent?) {
        intent?.let {
            val lat = it.getDoubleExtra("lat", 0.0)
            val lon = it.getDoubleExtra("lon", 0.0)

            val weatherData = fetchWeather(lat, lon)

            val broadcastIntent = Intent("WEATHER_UPDATE").apply {
                putExtra("weather", weatherData)
            }
            sendBroadcast(broadcastIntent)
        }
    }

    private fun fetchWeather(lat: Double, lon: Double): String {
        return try {
            val url = URL("https://api.weatherapi.com/v1/current.json?key=YOUR_API_KEY&q=$lat,$lon")
            val connection = url.openConnection() as HttpsURLConnection
            val reader = BufferedReader(InputStreamReader(connection.inputStream))
            val builder = StringBuilder()
            reader.forEachLine { builder.append(it) }
            builder.toString()
        } catch (e: Exception) {
            e.printStackTrace()
            "Ошибка загрузки данных"
        }
    }
}
