package com.example.weatherapp

@Parcelize
data class City(val city: String, val lat: Double, val lon: Double) : Parcelable

@Parcelize
data class Weather(val city: City) : Parcelable
