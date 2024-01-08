package com.example.semester.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.semester.data.models.DishCart
import com.example.semester.databinding.DishCartItemBinding

class DishCartAdapter :
    ListAdapter<DishCart, DishCartAdapter.DishCartViewHolder>(DishCartDiffUtil()) {
    class DishCartViewHolder(
        private val binding: DishCartItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dish: DishCart) = with(binding) {
            dishPhoto.load(dish.photoUrl)
            dishTitle.text = dish.title
            dishCost.text = "${dish.price * dish.count}"
        }
    }

    class DishCartDiffUtil : DiffUtil.ItemCallback<DishCart>() {
        override fun areItemsTheSame(oldItem: DishCart, newItem: DishCart): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: DishCart, newItem: DishCart): Boolean =
            newItem == oldItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishCartViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = DishCartItemBinding.inflate(inflater, parent, false)
        return DishCartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DishCartViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}