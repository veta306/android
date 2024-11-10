package com.example.lab3.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.lab3.models.Film

@Dao
interface FilmDao {
    @Insert
    suspend fun insertFilm(film: Film): Long

    @Insert
    suspend fun insertFilms(films: List<Film>)

    @Update
    suspend fun updateFilm(film: Film)

    @Query("SELECT * FROM films WHERE id = :id")
    suspend fun getFilm(id: Int): Film?

    @Query("SELECT * FROM films")
    suspend fun getAllFilms(): List<Film>

    @Delete
    suspend fun deleteFilm(film: Film)

    @Query("DELETE FROM films")
    suspend fun clearFilms()
}