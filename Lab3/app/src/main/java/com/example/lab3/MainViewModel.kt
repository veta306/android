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

            val films = listOf(
                Film(title = "Inception", releaseDate = "2010-07-16", description = "A mind-bending thriller that explores dreams within dreams, directed by Christopher Nolan."),
                Film(title = "The Matrix", releaseDate = "1999-03-31", description = "A dystopian future where reality is simulated by machines to subjugate humanity."),
                Film(title = "The Godfather", releaseDate = "1972-03-24", description = "An epic tale of crime and family loyalty within the Italian-American Mafia."),
                Film(title = "Interstellar", releaseDate = "2014-11-07", description = "An astronaut embarks on a journey to find a new home for humanity in distant galaxies."),
                Film(title = "The Dark Knight", releaseDate = "2008-07-18", description = "Batman faces his greatest challenge yet, the Joker, in a city plunged into chaos.")
            )

            // repository.insertFilms(films)

            val characters = listOf(
                Character(characterName = "Cobb", actorName = "Leonardo DiCaprio", filmId = 1),
                Character(characterName = "Arthur", actorName = "Joseph Gordon-Levitt", filmId = 1),
                Character(characterName = "Neo", actorName = "Keanu Reeves", filmId = 2),
                Character(characterName = "Morpheus", actorName = "Laurence Fishburne", filmId = 2),
                Character(characterName = "Vito Corleone", actorName = "Marlon Brando", filmId = 3),
                Character(characterName = "Michael Corleone", actorName = "Al Pacino", filmId = 3),
                Character(characterName = "Cooper", actorName = "Matthew McConaughey", filmId = 4),
                Character(characterName = "Brand", actorName = "Anne Hathaway", filmId = 4),
                Character(characterName = "Batman", actorName = "Christian Bale", filmId = 5),
                Character(characterName = "Joker", actorName = "Heath Ledger", filmId = 5)
            )

            // repository.insertCharacters(characters)

            getItems()
        }
    }

    private fun getItems(){
        viewModelScope.launch {
            _items.postValue(repository.getAllFilms().plus(repository.getAllCharacters()))
        }
    }

    fun addFilm(film: Film) {
        viewModelScope.launch {
            repository.insertFilm(film)
            getItems()
        }
    }

    fun addCharacter(character: Character) {
        viewModelScope.launch {
            repository.insertCharacter(character)
            getItems()
        }
    }

    fun updateFilm(film: Film) {
        viewModelScope.launch {
            repository.updateFilm(film)
            getItems()
        }
    }

    fun updateCharacter(character: Character) {
        viewModelScope.launch {
            repository.updateCharacter(character)
            getItems()
        }
    }

    fun deleteFilm(film: Film) {
        viewModelScope.launch {
            repository.deleteFilm(film)
            getItems()
        }
    }

    fun deleteCharacter(character: Character) {
        viewModelScope.launch {
            repository.deleteCharacter(character)
            getItems()
        }
    }
}
