package com.example.lab3.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(
    tableName = "characters",
    foreignKeys = [
        ForeignKey(
            entity = Film::class,
            parentColumns = ["id"],
            childColumns = ["filmId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["filmId"])]
)
data class Character(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @field:Json(name = "characterName") val characterName: String,
    @field:Json(name = "actorName") val actorName: String,
    @field:Json(name = "filmId") val filmId: Int
) : ItemInterface{
    override fun getItemType(): Int {
        return ItemInterface.character
    }
}