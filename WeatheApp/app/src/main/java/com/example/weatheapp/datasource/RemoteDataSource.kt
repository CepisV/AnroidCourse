package com.example.weatheapp.datasource

import com.example.weatheapp.RetrofitClient
import com.example.weatheapp.model.WeatherDTO
import retrofit2.Call
import retrofit2.Callback

class RemoteDataSource {

    private val apiKey = "2c7b94b12ba3c5ed1f381251d909e803"

    fun getWeatherDetails(lat: Double, lon: Double, callback: Callback<WeatherDTO>) {
        RetrofitClient.weatherApiService.getWeatherByCoordinates(
            lat = lat,
            lon = lon,
            apiKey = apiKey
        ).enqueue(callback)
    }
}
