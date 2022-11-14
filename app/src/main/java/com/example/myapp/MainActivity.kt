package com.example.myapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MyLog", "in onCreate")

        val registerButton = findViewById<Button>(R.id.main_activity_register_button) // Инициализировали объект кнопки
        val mainActivityLoginText = findViewById<TextView>(R.id.main_activity_login_text) // Указали объект кнопки на элемент внутри XML

        // Прикрепили обработчик нажатия к кнопке
        registerButton.setOnClickListener {
            Toast.makeText(this@MainActivity, "Go to Registration", Toast.LENGTH_SHORT).show()
            val intentToRegisterScreen = Intent(this, RegistrationActivity::class.java) // intent.putExtra("key", value)
            startActivity(intentToRegisterScreen)

        }

         mainActivityLoginText.setOnClickListener {
            Toast.makeText(this@MainActivity, "Go to Login", Toast.LENGTH_SHORT).show()
            val intentToLoginScreen = Intent(this, LoginActivity::class.java) // Пересылаем на экран Login
            //intent.putExtra("key", value)
            startActivity(intentToLoginScreen)
        }

    }
}