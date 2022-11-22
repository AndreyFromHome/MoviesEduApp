package com.example.myapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    // See: https://developer.android.com/training/basics/intents/result
    // Наше RegistrationActivity будет пер сылаться на активити, которое нам предоставляет Firebase
    private val signInLauncher = registerForActivityResult( // инициализируем (создаём) объект авторизации
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res) // запуск самого экрана
    }

    private lateinit var database: DatabaseReference // создали объект для записи в БД

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Choose authentication providers
        // Log.d("MyLog", "RegistrationActivity start registration")
        database = Firebase.database.reference // инициализация базы данных
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build() // создаём спсиок регистраций (только емейл)
        )
        // Create and launch sign-in intent
        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build() // создали intent для экрана firebase auth
        signInLauncher.launch(signInIntent) // запускаем экран firebase auth
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse // результат с экрана Firebase auth
        if (result.resultCode == RESULT_OK) {
            Log.d("MyLog", "RegistrationActivity registration success ${response?.email}")

            // Successfully signed in
            val authUser = FirebaseAuth.getInstance().currentUser // создаём объект текущего пользователя Firebase auth
            // Если authUser не является null, то выполняется всё внутри let {}
            authUser?.let { // если объект существует, сохраняем его в БД
                val email = it.email.toString()
                val uid = it.uid
                val fireBaseUser = User(email, uid) // создаём новый объект юзер с полученными параметрами
                Log.d("MyLog", "RegistrationActivity registration success $response")
                database.child("users").child(uid).setValue(fireBaseUser) // заполняем/сохраняем пользователя в Firebase realtime
                val intent = Intent(this@MainActivity, MoviesActivity::class.java)
                startActivity(intent)

            }

        } else {
            Log.d("MyLog", "RegistrationActivity registration failure")
            Toast.makeText(this@MainActivity, "Something wrong with registration", Toast.LENGTH_SHORT).show()
            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            // ...
        }
    }


}