package com.example.lab3.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.lab3.models.Film

@Dao
interface FilmDao {
    @Insert
    suspend fun insertFilm(film: Film): Long

    @Query("SELECT * FROM films WHERE id = :id")
    suspend fun getFilm(id: Int): Film?

    @Query("SELECT * FROM films")
    suspend fun getAllFilms(): List<Film>

    @Query("DELETE FROM films")
    suspend fun clearFilms()
}