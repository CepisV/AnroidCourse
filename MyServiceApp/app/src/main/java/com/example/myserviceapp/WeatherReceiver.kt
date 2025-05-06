package com.example.myserviceapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class WeatherReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "WEATHER_UPDATE") {
            val weather = intent.getStringExtra("weather")
            Toast.makeText(context, "Weather: $weather", Toast.LENGTH_LONG).show()
        }
    }
}
