package com.example.lab3.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "films")
data class Film (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val releaseDate: String,
    val description: String
): ItemInterface {
    override fun getItemType(): Int {
        return ItemInterface.film
    }
}