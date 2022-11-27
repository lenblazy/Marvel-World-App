package com.mwabonje.marvelworld.view.fragments.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mwabonje.marvelworld.database.MarvelEntity
import com.mwabonje.marvelworld.databinding.AdapterCharacterBinding

class CharactersAdapter(
    private val list: MutableList<MarvelEntity>,
    private val clickListener: CharacterListener,
) : RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    fun setCharactersList(updatedUserList: List<MarvelEntity>) {
        val diffResult = DiffUtil.calculateDiff(CharactersDiffCallback(list, updatedUserList))
        list.clear()
        list.addAll(updatedUserList)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount() = list.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder = ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(clickListener, list[position])

    class ViewHolder(private val binding: AdapterCharacterBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: CharacterListener, item: MarvelEntity) {
            binding.apply {
                val name = item.characterName
                tvInitial.text = name.substring(0, 2)
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

class CharactersDiffCallback(
    private val oldList: List<MarvelEntity>,
    private val newList: List<MarvelEntity>,
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }

}

/**
 * Handles clicks to the layout items
 */
class CharacterListener(val clickListener: (character: MarvelEntity) -> Unit) {
    fun onClick(character: MarvelEntity) = clickListener(character)
}