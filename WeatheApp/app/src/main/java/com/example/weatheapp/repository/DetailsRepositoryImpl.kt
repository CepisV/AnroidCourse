package com.example.weatheapp.repository

import com.example.weatheapp.model.WeatherDTO
import com.example.weatheapp.datasource.RemoteDataSource
import retrofit2.Callback

class DetailsRepositoryImpl(
    private val remoteDataSource: RemoteDataSource = RemoteDataSource()
) : DetailsRepository {

    override fun getWeatherDetailsFromServer(
        lat: Double,
        lon: Double,
        callback: Callback<WeatherDTO>
    ) {
        remoteDataSource.getWeatherDetails(lat, lon, callback)
    }
}
