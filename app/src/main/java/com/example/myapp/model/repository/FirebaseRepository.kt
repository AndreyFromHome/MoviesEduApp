package com.example.myapp.model.repository

import com.example.myapp.data.User

interface FirebaseRepository {
    fun updateUserData(firebaseUser: User, uid:String)
}