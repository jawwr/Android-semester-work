package com.example.semester.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.semester.R
import com.example.semester.data.models.Dish
import com.example.semester.data.models.DishCart
import com.example.semester.databinding.DishCardFragmentBinding
import com.example.semester.di.appComponent
import com.example.semester.di.viewModel.ViewModelFactory
import com.example.semester.presentation.viewModel.DishViewModel
import com.example.semester.utils.UiState
import javax.inject.Inject

class DishCardFragment : Fragment(R.layout.dish_card_fragment) {
    private val binding: DishCardFragmentBinding by viewBinding()
    private val args: DishCardFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: DishViewModel by viewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.dish.observe(viewLifecycleOwner) {
            showDish(it)
        }
        viewModel.dishInCart.observe(viewLifecycleOwner) {
            showCart(it)
        }
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
        viewModel.getDishById(args.dishId)
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    private fun showCart(uiState: UiState<DishCart>) {
        if (uiState is UiState.Success) {
            val dish = uiState.value
            binding.dishInCartCounter.count = dish.count
            binding.dishCost.text = "%,.2f".format(dish.price * dish.count)

            binding.dishInCartCounter.setOnIncreaseButtonClickListener {
                viewModel.updateDishInCart(dish.copy(count = dish.count + 1))

                viewModel.getDishInCartById(dish.dishId)
            }

            binding.dishInCartCounter.setOnDecreaseButtonClickListener {
                val count = if (binding.dishInCartCounter.count == 1) {
                    1
                } else {
                    binding.dishInCartCounter.count - 1
                }
                viewModel.updateDishInCart(dish.copy(count = count))
                viewModel.getDishInCartById(dish.dishId)
            }

        } else {
            binding.dishInCartCounter.count = 0
        }
    }

    private fun showDish(uiState: UiState<Dish>) = when (uiState) {
        is UiState.Success -> {
            val dish = uiState.value
            binding.dishCardTitle.text = dish.title
            binding.dishWeight.text = "${dish.mass}g"
            binding.dishCalories.text = "${dish.kcal} kcal"
            binding.dishIngredient.text = dish.description
            binding.dishPhoto.load(dish.photoUrl)
            binding.dishCost.text = "${dish.price}"

            viewModel.getDishInCartById(dish.id)

            binding.dishInCartCounter.setOnIncreaseButtonClickListener {
                if (binding.dishInCartCounter.count == 0) {
                    viewModel.upsertDishToCart(dish)
                }
                viewModel.getDishInCartById(dish.id)
            }

            binding.progressCategories.visibility = View.GONE
        }

        is UiState.Loading -> {
            binding.progressCategories.visibility = View.VISIBLE
        }

        is UiState.Failure -> {
            binding.progressCategories.visibility = View.GONE
        }
    }
}