package com.example.lab3.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lab3.databinding.ListItemCharacterBinding
import com.example.lab3.databinding.ListItemFilmBinding
import com.example.lab3.models.Character
import com.example.lab3.models.Film

class UniversalAdapter(private val items: List<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val CHARACTER = 0
    private val FILM = 1

    inner class CharacterViewHolder(
        private val binding: ListItemCharacterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character) {
            binding.apply {
                tvName.text = character.name
                tvHeight.text = character.height.toString()
                tvMass.text = character.mass.toString()
                tvBirth.text = character.birthYear
            }
        }

    }

    inner class FilmViewHolder(
        private val binding: ListItemFilmBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(film: Film) {
            binding.apply {
                tvTitle.text = film.title
                tvDate.text = film.date
                tvOpening.text = film.opening
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType){
            CHARACTER -> {
                return CharacterViewHolder(
                    ListItemCharacterBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            FILM -> {
                return FilmViewHolder(
                    ListItemFilmBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> throw IllegalArgumentException("Unknown viewType: $viewType")
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType){
            CHARACTER -> {
                (holder as CharacterViewHolder).bind(items[position] as Character)
            }
            FILM -> {
                (holder as FilmViewHolder).bind(items[position] as Film)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (items[position] is Character) {
            return CHARACTER
        } else if (items[position] is Film) {
            return FILM
        }
        return -1
    }
}