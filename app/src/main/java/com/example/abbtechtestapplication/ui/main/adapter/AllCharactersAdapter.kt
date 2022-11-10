package com.example.abbtechtestapplication.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.abbtechtestapplication.R
import com.example.abbtechtestapplication.models.Character
import com.squareup.picasso.Picasso

class AllCharactersAdapter :
    PagingDataAdapter<Character, AllCharactersAdapter.AllCharactersViewHolder>(COMPARATOR) {

    var onItemClick: ((id: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllCharactersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AllCharactersViewHolder(
            inflater.inflate(
                R.layout.character_info_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AllCharactersViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }

    inner class AllCharactersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val avatar = itemView.findViewById<ImageView>(R.id.avatar)
        private val characterClick = itemView.findViewById<ConstraintLayout>(R.id.character)
        private val name = itemView.findViewById<TextView>(R.id.name)
        private val gender = itemView.findViewById<TextView>(R.id.gender)
        private val status = itemView.findViewById<TextView>(R.id.status)
        fun bind(character: Character) {
            Picasso.get().load(character.image).into(avatar)
            name.text = character.name
            status.text =
                StringBuilder().append(character.status).append(" - ").append(character.species)
            gender.text = character.gender
            characterClick.setOnClickListener {
                onItemClick?.invoke(character.id)
            }
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem == newItem
            }
        }
    }
}