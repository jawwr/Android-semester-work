package com.example.semester.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
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
import com.example.semester.presentation.viewModel.OrderViewModel
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
    private val orderViewModel: OrderViewModel by viewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.dishInCart.observe(viewLifecycleOwner) {
            showDishes(it)
        }
        initRecycler()
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
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
            val totalCost = dishes.sumOf { it.price * it.count }
            binding.totalCost.text = "%,.2f".format(totalCost)

            binding.payButton.setOnClickListener {
                val ids = dishes.map { it.dishId }.toSet()
                orderViewModel.createOrder(ids)
                viewModel.deleteDishesByIdIn(ids)
            }

            binding.progressCategories.visibility = View.GONE
        }

        is UiState.Loading -> {
            binding.progressCategories.visibility = View.VISIBLE
        }

        is UiState.Failure -> {
            Toast.makeText(context, uiState.message, Toast.LENGTH_LONG).show()
            binding.progressCategories.visibility = View.VISIBLE
        }
    }

    private fun initRecycler() = with(binding.cartList) {
        adapter = this@DishCartFragment.adapter
        layoutManager = LinearLayoutManager(context)
    }
}