package com.example.lab3.repository

import com.example.lab3.api.AppApi
import com.example.lab3.dao.CharacterDao
import com.example.lab3.dao.FilmDao
import com.example.lab3.database.AppDatabase
import com.example.lab3.models.Character
import com.example.lab3.models.Film

class AppRepository(private val filmDao: FilmDao, private val characterDao: CharacterDao, private val appApi: AppApi) {

    suspend fun loadFilms(): List<Film>? {
        val response = appApi.getFilms()
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }

    suspend fun loadCharacters(): List<Character>? {
        val response = appApi.getCharacters()
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }

    suspend fun insertFilm(film: Film) {
        filmDao.insertFilm(film)
    }

    suspend fun insertFilms(films: List<Film>) {
        filmDao.insertFilms(films)
    }

    suspend fun insertCharacter(character: Character) {
        characterDao.insertCharacter(character)
    }

    suspend fun insertCharacters(characters: List<Character>) {
        characterDao.insertCharacters(characters)
    }

    suspend fun updateFilm(film: Film) {
        filmDao.updateFilm(film)
    }

    suspend fun updateCharacter(character: Character) {
        characterDao.updateCharacter(character)
    }

    suspend fun getAllFilms(): List<Film> {
        return filmDao.getAllFilms()
    }

    suspend fun getAllCharacters(): List<Character> {
        return characterDao.getAllCharacters()
    }

    suspend fun deleteFilm(film: Film) {
        filmDao.deleteFilm(film)
    }

    suspend fun deleteCharacter(character: Character) {
        characterDao.deleteCharacter(character)
    }

    suspend fun clearFilms() {
        filmDao.clearFilms()
    }

    suspend fun clearCharacters() {
        characterDao.clearCharacters()
    }
}
