package com.example.lab3

import android.app.Dialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lab3.adapters.UniversalAdapter
import com.example.lab3.databinding.ActivityMainBinding
import com.example.lab3.models.Film
import com.example.lab3.models.Character
import com.example.lab3.models.ItemInterface

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        baseContext.deleteDatabase("app_database");

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val adapter = UniversalAdapter(viewModel.items,
            onEditClicked = { item ->
                if (item is Film) showFilmDialog(item)
                else if (item is Character) showCharacterDialog(item) },
            onDeleteClicked = { item -> confirmDeleteItem(item) }
        )
        binding.fab.setOnClickListener {
            val options = arrayOf("Add Film", "Add Character")
            AlertDialog.Builder(this)
                .setTitle("Select Item to Add")
                .setItems(options) { _, which ->
                    when (which) {
                        0 -> showFilmDialog()
                        1 -> showCharacterDialog()
                        else -> return@setItems
                    }
                }.show()
        }
        binding.rv.adapter = adapter
        viewModel.items.observe(this){
            adapter.notifyDataSetChanged()
        }
    }

    private fun showFilmDialog(existingFilm: Film? = null) {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_add_film)

        val editTextTitle = dialog.findViewById<EditText>(R.id.editTextTitle)
        val editTextDate = dialog.findViewById<EditText>(R.id.editTextDate)
        val editTextDescription = dialog.findViewById<EditText>(R.id.editTextDescription)
        val buttonSave = dialog.findViewById<Button>(R.id.buttonSave)
        val buttonCancel = dialog.findViewById<Button>(R.id.buttonCancel)

        existingFilm?.let { film ->
            editTextTitle.setText(film.title)
            editTextDate.setText(film.releaseDate)
            editTextDescription.setText(film.description)
        }

        buttonSave.setOnClickListener {
            val title = editTextTitle.text.toString()
            val date = editTextDate.text.toString()
            val description = editTextDescription.text.toString()

            if (title.isNotBlank() && date.isNotBlank() && description.isNotBlank()) {
                if (existingFilm != null) {
                    val updatedFilm = existingFilm.copy(title = title, releaseDate = date, description = description)
                    viewModel.updateFilm(updatedFilm)
                } else {
                    val newFilm = Film(title = title, releaseDate = date, description = description)
                    viewModel.addFilm(newFilm)
                }
                dialog.dismiss()
            }
        }

        buttonCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun showCharacterDialog(existingCharacter: Character? = null) {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_add_character)

        val editTextCharacterName = dialog.findViewById<EditText>(R.id.editTextCharacterName)
        val editTextActorName = dialog.findViewById<EditText>(R.id.editTextActorName)
        val spinnerFilm = dialog.findViewById<Spinner>(R.id.spinnerFilm)
        val buttonSave = dialog.findViewById<Button>(R.id.buttonSave)
        val buttonCancel = dialog.findViewById<Button>(R.id.buttonCancel)

        viewModel.items.observe(this) { items ->
            val filmList = items.filterIsInstance<Film>()
            val filmTitles = filmList.map { it.title }
            val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, filmTitles)
            spinnerFilm.adapter = adapter

            existingCharacter?.let { character ->
                editTextCharacterName.setText(character.characterName)
                editTextActorName.setText(character.actorName)
                val filmIndex = filmList.indexOfFirst { it.id == character.filmId }
                if (filmIndex >= 0) spinnerFilm.setSelection(filmIndex)
            }
        }

        buttonSave.setOnClickListener {
            val characterName = editTextCharacterName.text.toString()
            val actorName = editTextActorName.text.toString()
            val selectedFilmPosition = spinnerFilm.selectedItemPosition

            if (characterName.isNotBlank() && actorName.isNotBlank() && selectedFilmPosition >= 0) {
                viewModel.items.value?.let { items ->
                    val filmList = items.filterIsInstance<Film>()
                    val selectedFilm = filmList[selectedFilmPosition]

                    if (existingCharacter != null) {
                        val updatedCharacter = existingCharacter.copy(
                            characterName = characterName,
                            actorName = actorName,
                            filmId = selectedFilm.id
                        )
                        viewModel.updateCharacter(updatedCharacter)
                    } else {
                        val newCharacter = Character(
                            characterName = characterName,
                            actorName = actorName,
                            filmId = selectedFilm.id
                        )
                        viewModel.addCharacter(newCharacter)
                    }
                }
                dialog.dismiss()
            }
        }

        buttonCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun confirmDeleteItem(item: ItemInterface) {
        if (item is Film) {
            viewModel.deleteFilm(item)
            Toast.makeText(this, "Film deleted", Toast.LENGTH_SHORT).show()
        } else if (item is Character) {
            viewModel.deleteCharacter(item)
            Toast.makeText(this, "Character deleted", Toast.LENGTH_SHORT).show()
        }
    }
}