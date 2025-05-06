package com.example.weatheapp.repository

import retrofit2.Callback
import com.example.weatheapp.model.WeatherDTO

interface DetailsRepository {
    fun getWeatherDetailsFromServer(
        lat: Double,
        lon: Double,
        callback: Callback<WeatherDTO>
    )
}
