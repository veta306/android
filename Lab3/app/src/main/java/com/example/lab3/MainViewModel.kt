package com.example.lab3

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.lab3.database.AppDatabase
import com.example.lab3.models.Character
import com.example.lab3.models.Film
import com.example.lab3.models.ItemInterface
import com.example.lab3.repository.AppRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val database = Room.databaseBuilder(
        application,
        AppDatabase::class.java, "app_database"
    ).build()

    private val repository = AppRepository(database)

    private var _items: MutableLiveData<List<ItemInterface>> =
        MutableLiveData<List<ItemInterface>>().apply {
            value = emptyList()
        }
    val items: LiveData<List<ItemInterface>> = _items

    init {
        viewModelScope.launch {
            // repository.clearFilms()
            // repository.clearCharacters()

            val film = Film(title = "Inception", releaseDate = "2010-07-16", description = "A mind-bending thriller.")

            repository.insertFilm(film)
            _items.postValue(repository.getAllFilms())
        }
    }
}
