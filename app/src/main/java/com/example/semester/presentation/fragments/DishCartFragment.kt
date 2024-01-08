package com.example.semester.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.semester.R
import com.example.semester.data.models.DishCart
import com.example.semester.databinding.CartFragmentBinding
import com.example.semester.di.appComponent
import com.example.semester.di.viewModel.ViewModelFactory
import com.example.semester.presentation.adapters.DishCartAdapter
import com.example.semester.presentation.viewModel.DishCartViewModel
import com.example.semester.utils.UiState
import javax.inject.Inject

class DishCartFragment : Fragment(R.layout.cart_fragment) {
    private val binding: CartFragmentBinding by viewBinding()
    private val adapter: DishCartAdapter = DishCartAdapter(
        ::onDecreaseDish,
        ::onIncreaseDish,
        ::onCloseButtonClick
    )

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: DishCartViewModel by viewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.dishInCart.observe(viewLifecycleOwner) {
            showDishes(it)
        }
        initRecycler()
        viewModel.getAllDishInCart()
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    private fun onDecreaseDish(dish: DishCart) {
        if (dish.count == 1) {
            viewModel.deleteDishFromCart(dish)
        } else {
            viewModel.updateDishCart(
                dish.copy(count = dish.count - 1)
            )
        }
        viewModel.getAllDishInCart()
    }

    private fun onIncreaseDish(dish: DishCart) {
        viewModel.updateDishCart(
            dish.copy(count = dish.count + 1)
        )
        viewModel.getAllDishInCart()
    }

    private fun onCloseButtonClick(dish: DishCart) {
        viewModel.deleteDishFromCart(dish)
        viewModel.getAllDishInCart()
    }

    private fun showDishes(uiState: UiState<List<DishCart>>) = when (uiState) {
        is UiState.Success -> {
            val dishes = uiState.value
            adapter.submitList(dishes)

            binding.progressCategories.visibility = View.GONE
        }

        is UiState.Loading -> {
            binding.progressCategories.visibility = View.VISIBLE
        }

        is UiState.Failure -> {
            binding.progressCategories.visibility = View.GONE
        }
    }

    private fun initRecycler() = with(binding.cartList) {
        adapter = this@DishCartFragment.adapter
        layoutManager = LinearLayoutManager(context)
    }
}