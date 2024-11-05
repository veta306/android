package com.example.lab3.models

data class Film (val title: String, val date: String, val opening: String): ItemInterface {
    override fun getItemType(): Int {
        return ItemInterface.film
    }
}