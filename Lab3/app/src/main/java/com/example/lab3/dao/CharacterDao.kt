package com.example.lab3.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.lab3.models.Character

@Dao
interface CharacterDao {
    @Insert
    suspend fun insertCharacter(character: Character): Long

    @Query("SELECT * FROM characters WHERE id = :id")
    suspend fun getCharacter(id: Int): Character?

    @Query("SELECT * FROM characters WHERE filmId = :filmId")
    suspend fun getCharactersByFilm(filmId: Int): List<Character>

    @Query("SELECT * FROM characters")
    suspend fun getAllCharacters(): List<Character>

    @Query("DELETE FROM characters")
    suspend fun clearCharacters()
}