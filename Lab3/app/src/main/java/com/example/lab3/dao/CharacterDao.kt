package com.example.lab3.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.lab3.models.Character

@Dao
interface CharacterDao {
    @Insert
    suspend fun insertCharacter(character: Character): Long

    @Insert
    suspend fun insertCharacters(characters: List<Character>)

    @Update
    suspend fun updateCharacter(character: Character)

    @Query("SELECT * FROM characters")
    suspend fun getAllCharacters(): List<Character>

    @Delete
    suspend fun deleteCharacter(character: Character)

    @Query("DELETE FROM characters")
    suspend fun clearCharacters()
}