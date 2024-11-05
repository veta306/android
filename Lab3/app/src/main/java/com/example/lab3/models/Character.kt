package com.example.lab3.models

data class Character(
    val name: String,
    val height: Int?,
    val mass: Int?,
    val birthYear: String
) : ItemInterface{
    override fun getItemType(): Int {
        return ItemInterface.character
    }
}