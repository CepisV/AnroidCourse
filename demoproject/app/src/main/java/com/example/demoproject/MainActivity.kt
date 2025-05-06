package com.example.demoproject

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appName = getString(R.string.app_name)
        val apiUrl = BuildConfig.API_URL

        val textView: TextView = findViewById(R.id.textView)
        textView.text = "App Name: $appName\nAPI URL: $apiUrl"
    }
}
