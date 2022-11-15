package com.example.myapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

class RegistrationActivity : AppCompatActivity() {

    // See: https://developer.android.com/training/basics/intents/result
    // Наше RegistrationActivity будет пер сылаться на активити, которое нам предоставляет Firebase
    private val signInLauncher = registerForActivityResult( // инициализируем
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res)
    }

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        // Choose authentication providers
        Log.d("MyLog", "RegistrationActivity start registration")
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build()
        )

        // Create and launch sign-in intent
        // Создание интента с использованием билдера, который будет строить интент из библиотеку Firebase
        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
        signInLauncher.launch(signInIntent) // запускаем
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK) {
            Log.d("MyLog", "RegistrationActivity registration success ${response?.email}")

            // Successfully signed in
            val authUser = FirebaseAuth.getInstance().currentUser

            // Если authUser не является null, то выполняется всё внутри let {}
            authUser?.let {
                User(it.email.toString(), it.uid) // создаём юзера с полученными данными

                database.child("users").child(userId).setValue(user)
            }

        } else {
            Log.d("MyLog", "RegistrationActivity registration failure")
            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            // ...
        }
    }


}