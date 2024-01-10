package com.example.semester.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.semester.databinding.ItemCartCounterBinding


class ItemCartCounter @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private val binding = ItemCartCounterBinding.inflate(LayoutInflater.from(context), this, true)

    private var onDecreaseButtonClick: () -> Unit = {}
    private var onIncreaseButtonClick: () -> Unit = {}

    fun setOnDecreaseButtonClickListener(func: () -> Unit) {
        onDecreaseButtonClick = func
    }

    fun setOnIncreaseButtonClickListener(func: () -> Unit) {
        onIncreaseButtonClick = func
    }

    var count: Int = 0
        set(value) {
            binding.count.text = "$value"
            field = value
        }

    init {
        binding.decreaseButton.setOnClickListener {
            onDecreaseButtonClick()
        }
        binding.increaseButton.setOnClickListener {
            onIncreaseButtonClick()
        }
    }
}