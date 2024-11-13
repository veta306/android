package com.example.lab3

import android.app.Application
import com.example.lab3.database.AppDatabase
import com.example.lab3.repository.AppRepository

class MainApp: Application() {
    private val database by lazy { AppDatabase.getDatabase(this)}
    val repository by lazy { AppRepository(database.filmDao(), database.characterDao()) }
}