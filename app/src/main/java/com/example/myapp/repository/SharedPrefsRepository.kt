package com.example.myapp.repository

interface SharedPrefsRepository {
    fun saveInPrefs(value: String) : String
}