package com.example.myapp.model.repository

interface SharedPrefsRepository {
    fun saveInPrefs(value: String) : String
}