package com.example.semester.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.semester.data.models.Dish
import com.example.semester.databinding.MainScreenCardFragmentBinding

class CardAdapter : ListAdapter<Dish, CardAdapter.CardViewHolder>(CardDiffUtil()) {
    class CardViewHolder(
        private val binding: MainScreenCardFragmentBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(card: Dish) = with(binding) {
            foodName.text = card.title
            dishPrice.text = "${card.price}"
            foodImage.load(card.photoUrl)
        }
    }

    class CardDiffUtil : DiffUtil.ItemCallback<Dish>() {
        override fun areItemsTheSame(oldItem: Dish, newItem: Dish): Boolean =
            newItem == oldItem

        override fun areContentsTheSame(oldItem: Dish, newItem: Dish): Boolean =
            newItem == oldItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = MainScreenCardFragmentBinding.inflate(inflater, parent, false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}