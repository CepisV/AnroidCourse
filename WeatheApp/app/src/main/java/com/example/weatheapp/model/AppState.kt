package com.example.weatheapp.model

sealed class AppState {
    object Loading : AppState()
    data class Success(val weatherData: WeatherDTO) : AppState()
    data class Error(val error: Throwable) : AppState()
}
