package com.example.semester.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.semester.R
import com.example.semester.data.models.Dish
import com.example.semester.databinding.MainScreenFragmentBinding
import com.example.semester.di.appComponent
import com.example.semester.di.viewModel.ViewModelFactory
import com.example.semester.presentation.adapters.CardAdapter
import com.example.semester.presentation.viewModel.MainDishCardViewModel
import com.example.semester.utils.UiState
import javax.inject.Inject

class MainFragment : Fragment(R.layout.main_screen_fragment) {
    private val binding: MainScreenFragmentBinding by viewBinding()
    private val adapter: CardAdapter = CardAdapter(::onCardClick)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: MainDishCardViewModel by viewModels { viewModelFactory }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        viewModel.dishesInMainScreen.observe(viewLifecycleOwner) {
            showDishes(it)
        }
        viewModel.getAllDish()
        binding.cart.setOnClickListener {
            onCartClick()
        }
        binding.orders.setOnClickListener {
            onOrderClick()
        }
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    private fun showDishes(uiState: UiState<List<Dish>>) = when (uiState) {
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

    private fun onOrderClick() {
        val direction = MainFragmentDirections.actionMainFragmentToOrderFragment()
        findNavController().navigate(direction)
    }

    private fun onCartClick() {
        val direction = MainFragmentDirections.actionMainFragmentToDishCartFragment()
        findNavController().navigate(direction)
    }

    private fun onCardClick(dish: Dish) {
        val direction = MainFragmentDirections.actionMainFragmentToDishCardFragment(
            dishId = dish.id
        )
        findNavController().navigate(direction)
    }

    private fun initRecycler() = with(binding.foodList) {
        adapter = this@MainFragment.adapter
        layoutManager = GridLayoutManager(context, 2)
    }
}