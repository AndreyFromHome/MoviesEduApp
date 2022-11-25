package com.example.myapp.viewmodel

import com.example.myapp.data.User
import com.example.myapp.model.repository.FirebaseRepository
import com.example.myapp.model.repository.FirebaseRepositoryImpl

class MainActivityViewModel {
    private val mFirebaseRepository: FirebaseRepository = FirebaseRepositoryImpl()

    fun updateUserData(firebase: User, uid: String) {
        mFirebaseRepository.updateUserData(firebase, uid)
    }
}