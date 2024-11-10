package com.example.lab3.adapters

import android.view.Gravity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.lab3.R
import com.example.lab3.databinding.ListItemCharacterBinding
import com.example.lab3.databinding.ListItemFilmBinding
import com.example.lab3.models.Character
import com.example.lab3.models.Film
import com.example.lab3.models.ItemInterface

class UniversalAdapter(
    private val items: LiveData<List<ItemInterface>>,
    private val onEditClicked: (ItemInterface) -> Unit,
    private val onDeleteClicked: (ItemInterface) -> Unit
) : RecyclerView.Adapter<UniversalAdapter.BaseViewHolder>() {

    abstract class BaseViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(item: ItemInterface)
    }

    inner class CharacterViewHolder(
        private val binding: ListItemCharacterBinding
    ) : BaseViewHolder(binding) {

        override fun bind(item: ItemInterface) {
            val character = item as Character
            binding.apply {
                tvName.text = character.characterName
                tvActor.text = character.actorName
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

        holder.itemView.setOnLongClickListener {
            showPopupMenu(it, items.value!![position])
            true
        }
    }

    override fun getItemViewType(position: Int): Int {
        return items.value!![position].getItemType()
    }

    private fun showPopupMenu(view: View, item: ItemInterface) {
        val popupMenu = PopupMenu(view.context, view, Gravity.END)
        popupMenu.inflate(R.menu.menu_edit_delete)
        popupMenu.setOnMenuItemClickListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.action_edit -> onEditClicked(item)
                R.id.action_delete -> onDeleteClicked(item)
            }
            true
        }
        popupMenu.show()
    }
}
