package com.mwabonje.marvelworld.view.fragments.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mwabonje.marvelworld.databinding.AdapterDetailsBinding
import com.mwabonje.marvelworld.databinding.AdapterHeaderBinding

enum class RowType {
    Detail,
    Header
}

sealed class DetailRow(val rowType: RowType) {
    data class Detail(val content: String) :
        DetailRow(RowType.Detail)

    data class Header(val type: String) : DetailRow(RowType.Header)
}

class CharacterDetailAdapter( private val list: MutableList<DetailRow>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount() = list.size

    override fun getItemViewType(position: Int): Int =
        list[position].rowType.ordinal

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder = when (RowType.values()[viewType]) {
        RowType.Detail -> DetailViewHolder.from(parent)
        RowType.Header -> HeaderViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (val detailRow = list[position]) {
            is DetailRow.Header -> (holder as HeaderViewHolder).bind(detailRow)
            is DetailRow.Detail -> (holder as DetailViewHolder).bind(detailRow)
        }

    class DetailViewHolder(private val binding: AdapterDetailsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(detail: DetailRow.Detail) {
            binding.tvName.text = detail.content
        }

        companion object {
            fun from(parent: ViewGroup): DetailViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = AdapterDetailsBinding.inflate(layoutInflater, parent, false)
                return DetailViewHolder(binding)
            }
        }
    }

    class HeaderViewHolder(private val binding: AdapterHeaderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(detail: DetailRow.Header) {
            binding.tvName.text = detail.type
        }

        companion object {
            fun from(parent: ViewGroup): HeaderViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = AdapterHeaderBinding.inflate(layoutInflater, parent, false)
                return HeaderViewHolder(binding)
            }
        }
    }

}
