package com.example.weatheapp.model

import com.google.gson.annotations.SerializedName

data class WeatherDTO(
    @SerializedName("main")
    val main: MainDTO,
    @SerializedName("weather")
    val weather: List<WeatherDescriptionDTO>
)

data class MainDTO(
    @SerializedName("temp")
    val temp: Double
)

data class WeatherDescriptionDTO(
    @SerializedName("description")
    val description: String
)
