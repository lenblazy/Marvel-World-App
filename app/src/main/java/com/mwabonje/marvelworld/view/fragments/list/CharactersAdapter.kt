package com.mwabonje.marvelworld.view.fragments.list

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mwabonje.marvelworld.database.MarvelEntity
import com.mwabonje.marvelworld.databinding.AdapterCharacterBinding

class CharactersAdapter(
    val list: List<MarvelEntity>,
    private val clickListener: CharacterListener,
) :
    ListAdapter<MarvelEntity, CharactersAdapter.ViewHolder>(CharactersDiffCallback()) {

    var data = list
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder = ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(clickListener, data[position])

    class ViewHolder(val binding: AdapterCharacterBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: CharacterListener, item: MarvelEntity) {
            binding.apply {
                val name = item.characterName
                Log.d("APAPA", "JINA NI $name")
                tvInitial.text = name.substring(0, 1)
                tvName.text = name
                clHolder.setOnClickListener { clickListener.onClick(item) }
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = AdapterCharacterBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }

}

class CharactersDiffCallback : DiffUtil.ItemCallback<MarvelEntity>() {

    override fun areItemsTheSame(oldItem: MarvelEntity, newItem: MarvelEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MarvelEntity, newItem: MarvelEntity): Boolean {
        return oldItem == newItem
    }
}

/**
 * Handles clicks to the layout items
 */
class CharacterListener(val clickListener: (character: MarvelEntity) -> Unit) {
    fun onClick(character: MarvelEntity) = clickListener(character)
}