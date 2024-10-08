package com.turing.alan.cpifp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.turing.alan.cpifp.data.Champion
import com.turing.alan.cpifp.databinding.ChampionListItemBinding

class ChampionListAdapter(private val toChampionDetail:((Champion)->Unit)): ListAdapter<Champion, ChampionListAdapter.ChampionViewHolder>(ChampionDiffCallback) {
    inner class ChampionViewHolder(private val binding: ChampionListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(champion: Champion) {
            binding.championImage.load(champion.imageUrl)
            binding.championName.text = champion.name
            binding.championTitle.text = champion.title
            // binding.championLore.text = champion.lore
            binding.root.setOnClickListener  {
                toChampionDetail(champion)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChampionViewHolder {
        val binding:ChampionListItemBinding = ChampionListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ChampionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChampionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object ChampionDiffCallback: DiffUtil.ItemCallback<Champion>() {
        override fun areItemsTheSame(oldItem: Champion, newItem: Champion) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Champion, newItem: Champion) =
                oldItem.imageUrl == newItem.imageUrl &&
                oldItem.name == newItem.name &&
                oldItem.title == newItem.title
    }
}