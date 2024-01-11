package com.example.semester.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.semester.data.models.Order
import com.example.semester.databinding.OrderItemBinding

class OrderAdapter : ListAdapter<Order, OrderAdapter.OrderViewHolder>(OrderDiffUtils()) {
    class OrderViewHolder(
        private val binding: OrderItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(order: Order) = with(binding) {
            orderId.text = "#${order.id}"
            orderStatus.text = order.status.value
        }
    }

    class OrderDiffUtils : DiffUtil.ItemCallback<Order>() {
        override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean = oldItem == newItem

        override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = OrderItemBinding.inflate(inflater, parent, false)
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) =
        holder.bind(getItem(position))
}