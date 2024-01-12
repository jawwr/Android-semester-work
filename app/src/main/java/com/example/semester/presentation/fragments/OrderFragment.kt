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
import com.example.semester.data.models.Order
import com.example.semester.databinding.OrderFragmentBinding
import com.example.semester.di.appComponent
import com.example.semester.di.viewModel.ViewModelFactory
import com.example.semester.presentation.adapters.OrderAdapter
import com.example.semester.presentation.viewModel.OrderViewModel
import com.example.semester.utils.UiState
import javax.inject.Inject

class OrderFragment : Fragment(R.layout.order_fragment) {
    private val binding: OrderFragmentBinding by viewBinding()
    private val adapter = OrderAdapter()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: OrderViewModel by viewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.orders.observe(viewLifecycleOwner) {
            showOrders(it)
        }
        initRecycler()
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
        viewModel.findAllOrders()
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    private fun showOrders(uiState: UiState<List<Order>>) = when (uiState) {
        is UiState.Success -> {
            val orders = uiState.value
            adapter.submitList(orders)

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

    private fun initRecycler() = with(binding.orderList) {
        adapter = this@OrderFragment.adapter
        layoutManager = LinearLayoutManager(context)
    }
}