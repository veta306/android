package com.example.lab3.repository

import com.example.lab3.database.AppDatabase
import com.example.lab3.models.Character
import com.example.lab3.models.Film

class AppRepository(private val database: AppDatabase) {

    suspend fun insertFilm(film: Film) {
        database.filmDao().insertFilm(film)
    }

    suspend fun insertCharacter(character: Character) {
        database.characterDao().insertCharacter(character)
    }

    suspend fun getAllFilms(): List<Film> {
        return database.filmDao().getAllFilms()
    }

    suspend fun getAllCharacters(): List<Character> {
        return database.characterDao().getAllCharacters()
    }

    suspend fun clearFilms() {
        database.filmDao().clearFilms()
    }

    suspend fun clearCharacters() {
        database.characterDao().clearCharacters()
    }
}
