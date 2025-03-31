package com.example.hw1

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.editText)
        val button = findViewById<Button>(R.id.button)
        val switch1 = findViewById<Switch>(R.id.switch1)
        val checkBox = findViewById<CheckBox>(R.id.checkBox)
        val toggleButton = findViewById<ToggleButton>(R.id.toggleButton)
        val calendarView = findViewById<CalendarView>(R.id.calendarView)

        button.setOnClickListener {
            val text = editText.text.toString()
            Toast.makeText(this, "Вы ввели: $text", Toast.LENGTH_SHORT).show()
        }

        switch1.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(this, if (isChecked) "Switch Включен" else "Switch Выключен", Toast.LENGTH_SHORT).show()
        }

        checkBox.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(this, if (isChecked) "Чекбокс выбран" else "Чекбокс снят", Toast.LENGTH_SHORT).show()
        }

        toggleButton.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(this, if (isChecked) "Toggle Включен" else "Toggle Выключен", Toast.LENGTH_SHORT).show()
        }

        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            Toast.makeText(this, "Дата: $dayOfMonth.${month + 1}.$year", Toast.LENGTH_SHORT).show()
        }

        button.setOnLongClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
            true
        }
    }
}
