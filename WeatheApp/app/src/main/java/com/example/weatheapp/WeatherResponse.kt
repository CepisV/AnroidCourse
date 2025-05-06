package com.example.weatheapp


data class WeatherResponse(
    val main: Main,
    val weather: List<WeatherDescription>
)

data class Main(
    val temp: Double
)

data class WeatherDescription(
    val description: String
)
