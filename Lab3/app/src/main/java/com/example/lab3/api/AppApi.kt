package com.example.lab3.api

import com.example.lab3.models.Character
import com.example.lab3.models.Film
import retrofit2.Response
import retrofit2.http.GET

interface AppApi {
    @GET("films")
    suspend fun getFilms(): Response<List<Film>>

    @GET("characters")
    suspend fun getCharacters(): Response<List<Character>>
}