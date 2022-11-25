package com.example.myapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.myapp.R
import com.example.myapp.data.User
import com.example.myapp.viewmodel.MainActivityViewModel
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private val mMainActivityViewModel : MainActivityViewModel = MainActivityViewModel()

    private val signInLauncher = registerForActivityResult( // инициализируем (создаём) объект авторизации
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res) // запуск самого экрана
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openRegistrationScreen()
    }
    /**
     *  We make a call to Firebase auth API to show dialog for registration
     */

    private fun openRegistrationScreen() {
        val intentToAnotherScreen = Intent (this, MoviesActivity::class.java)
        startActivity(intentToAnotherScreen)

        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build() // создаём спсиок регистраций (только емейл)
        )

        val signInIntent = AuthUI.getInstance()// intent для экрана авторизации
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build() // создали intent для экрана firebase auth
        signInLauncher.launch(signInIntent) // запускаем экран firebase auth
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        //val response = result.idpResponse // результат с экрана Firebase auth
        when (result.resultCode) {
            RESULT_OK -> {
                // Log.d("MyLog", "RegistrationActivity registration success ${response?.email}")
                // Successfully signed in
                val authUser = FirebaseAuth.getInstance().currentUser // создаём объект текущего пользователя Firebase auth
                // Если authUser не является null, то выполняется всё внутри let {}
                authUser?.let { // если объект существует, сохраняем его в БД
                    val email = it.email.toString()
                    val uid = it.uid
                    val fireBaseUser = User(email, uid) // создаём новый объект юзер с полученными параметрами
                    //Log.d("MyLog", "RegistrationActivity registration success $response")

                    mMainActivityViewModel.updateUserData(firebaseUser, uid) // заполняем/сохраняем пользователя в Firebase realtime

                    val intentToAnotherScreen = Intent(this@MainActivity, MoviesActivity::class.java)
                    startActivity(intentToAnotherScreen)
                }
            }
            RESULT_CANCELED -> {
                //   Log.d("MyLog", "RegistrationActivity registration failure")
                Toast.makeText(this@MainActivity, "Something wrong with registration", Toast.LENGTH_SHORT).show()
            }
            else -> {
              // do not do anything
            }
        }
    }
}