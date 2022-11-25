package com.example.myapp.model.repository

interface SharedPrefsRepository {
    fun saveInPrefs(key: String)

    fun getFromPrefs(key: String) : String
}