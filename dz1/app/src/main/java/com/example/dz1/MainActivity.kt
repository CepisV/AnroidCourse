package com.example.dz1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dz1.ui.theme.Dz1Theme

class MainActivity : ComponentActivity() {

    data class Product(val name: String, val price: Double)
    class User(val name: String, val age: Int)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Dz1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    KotlinBasics()
                }
            }
        }
    }

    @Composable
    fun KotlinBasics() {
        val scrollState = rememberScrollState()
        val output = buildString {
            // 1
            val age: Int = 35
            val height: Double = 1.87
            val name: String = "Дин"
            val isStudent: Boolean = true

            appendLine("1. Переменные:")
            appendLine("Возраст: $age")
            appendLine("Рост: $height")
            appendLine("Имя: $name")
            appendLine("Студент: $isStudent\n")

            // 2
            fun categorizeAge(age: Int): String {
                return if (age < 13) "Ребёнок"
                else if (age < 18) "Подросток"
                else "Взрослый"
            }

            appendLine("2. Категории возраста:")
            appendLine("10 лет: ${categorizeAge(10)}")
            appendLine("15 лет: ${categorizeAge(15)}")
            appendLine("25 лет: ${categorizeAge(25)}\n")

            // 3
            appendLine("3. Таблица умножения на 5:")
            for (i in 1..10) {
                appendLine("5 x $i = ${5 * i}")
            }
            appendLine()

            // 4
            fun sum(a: Int, b: Int) = a + b
            appendLine("4. Сумма 3 и 7: ${sum(3, 7)}\n")

            // 5
            val user = User("Джон", 26)
            appendLine("5. Класс User:")
            appendLine("Имя: ${user.name}, Возраст: ${user.age}\n")

            // 8
            fun getDayType(day: String): String {
                return when (day.lowercase()) {
                    "понедельник", "вторник", "среда", "четверг", "пятница" -> "Рабочий день"
                    "суббота", "воскресенье" -> "Выходной"
                    else -> "Неизвестный день"
                }
            }

            appendLine("8. Тип дня:")
            appendLine("понедельник: ${getDayType("понедельник")}")
            appendLine("воскресенье: ${getDayType("воскресенье")}")
            appendLine("среда: ${getDayType("среда")}")
            appendLine()

            // 10
            val product = Product("Ноутбук", 10000.00)
            val (productName, productPrice) = product
            appendLine("10. Data class:")
            appendLine("Название: $productName")
            appendLine("Цена: $productPrice руб.")
        }

        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(scrollState)
        ) {
            Text(text = "Примеры Kotlin", fontSize = 22.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = output, fontSize = 16.sp)
        }
    }
}
