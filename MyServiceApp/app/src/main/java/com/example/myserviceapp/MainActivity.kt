package com.example.myserviceapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var startServiceButton: Button
    private lateinit var stopServiceButton: Button
    private lateinit var startWeatherServiceButton: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var textView: TextView

    private val weatherReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == "WEATHER_UPDATE") {
                val weather = intent.getStringExtra("weather")
                Toast.makeText(context, "Weather: $weather", Toast.LENGTH_LONG).show()
                textView.text = weather
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startServiceButton = findViewById(R.id.startServiceButton)
        stopServiceButton = findViewById(R.id.stopServiceButton)
        startWeatherServiceButton = findViewById(R.id.startWeatherServiceButton)
        progressBar = findViewById(R.id.progressBar)
        textView = findViewById(R.id.textView)

        startServiceButton.setOnClickListener {
            startService(Intent(this, MyService::class.java))
        }

        stopServiceButton.setOnClickListener {
            stopService(Intent(this, MyService::class.java))
        }

        startWeatherServiceButton.setOnClickListener {
            val intent = Intent(this, WeatherService::class.java).apply {
                putExtra("lat", 55.7558)
                putExtra("lon", 37.6176)
            }
            startService(intent)
        }

        MyAsyncTask(progressBar, textView).execute()
    }

    override fun onStart() {
        super.onStart()
        registerReceiver(weatherReceiver, IntentFilter("WEATHER_UPDATE"))
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(weatherReceiver)
    }
}
