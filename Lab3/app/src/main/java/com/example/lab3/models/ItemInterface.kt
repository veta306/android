package com.example.lab3.models

interface ItemInterface {
    fun getItemType(): Int

    companion object{
        const val film = 1
        const val character = 2
    }
}