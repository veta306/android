package com.example.lab3.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.lab3.databinding.ListItemCharacterBinding
import com.example.lab3.databinding.ListItemFilmBinding
import com.example.lab3.models.Character
import com.example.lab3.models.Film
import com.example.lab3.models.ItemInterface

class UniversalAdapter(private val items: LiveData<List<ItemInterface>>) : RecyclerView.Adapter<UniversalAdapter.BaseViewHolder>() {

    abstract class BaseViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(item: ItemInterface)
    }

    inner class CharacterViewHolder(
        private val binding: ListItemCharacterBinding
    ) : BaseViewHolder(binding) {

        override fun bind(item: ItemInterface) {
            val character = item as Character
            binding.apply {
                tvName.text = character.name
                tvRole.text = character.role
                tvAppearance.text = character.appearance
            }
        }
    }

    inner class FilmViewHolder(
        private val binding: ListItemFilmBinding
    ) : BaseViewHolder(binding) {

        override fun bind(item: ItemInterface) {
            val film = item as Film
            binding.apply {
                tvTitle.text = film.title
                tvDate.text = film.releaseDate
                tvDescription.text = film.description
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            ItemInterface.character -> {
                CharacterViewHolder(
                    ListItemCharacterBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            ItemInterface.film -> {
                FilmViewHolder(
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

    override fun getItemCount(): Int = items.value!!.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(items.value!![position])
    }

    override fun getItemViewType(position: Int): Int {
        return items.value!![position].getItemType()
    }
}
