package com.example.lab3.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "films")
data class Film (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "releaseDate") val releaseDate: String,
    @field:Json(name = "description") val description: String
): ItemInterface {
    override fun getItemType(): Int {
        return ItemInterface.film
    }
}