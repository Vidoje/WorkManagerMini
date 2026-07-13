package com.taurunium.punmikuracindijaca.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.taurunium.punmikuracindijaca.R
import com.taurunium.punmikuracindijaca.adapter.ProductAdapter
import com.taurunium.punmikuracindijaca.databinding.FragmentProductBinding
import com.taurunium.punmikuracindijaca.viewmodels.ProductSharedViewModel
import kotlinx.coroutines.launch


class ProductFragment : Fragment(R.layout.fragment_product) {

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: ProductSharedViewModel by activityViewModels()

    private val allProducts = listOf(
        "Electronics Headphones",
        "Apple iPhone 15 Pro",
        "Samsung Galaxy S24",
        "Logitech MX Master 3S Mouse",
        "Nike Air Max Sneakers",
        "Levi's 501 Original Jeans",
        "Introduction to Kotlin Book",
        "Clean Code by Robert C. Martin"
    )

    // Use our new proper RecyclerView adapter
    private lateinit var productAdapter: ProductAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProductBinding.bind(view)

        setupRecyclerView()
        observeFilterChanges()
    }

    private fun setupRecyclerView() {
        productAdapter = ProductAdapter()

        // CRITICAL FIX: Explicitly assign the LayoutManager right here
        binding.productRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(requireContext())

        binding.productRecyclerView.adapter = productAdapter
    }

    private fun observeFilterChanges() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                sharedViewModel.filterQuery.collect { query ->
                    filterAndDisplayList(query)
                }
            }
        }
    }

    private fun filterAndDisplayList(query: String) {
        // Trim any accidental white spaces from the input layout
        val cleanQuery = query.trim()

        val filteredList = if (cleanQuery.isEmpty()) {
            binding.currentFilterTextView.text = "Showing all products"
            allProducts // Directly pass the full raw list
        } else {
            binding.currentFilterTextView.text = "Filtering by: \"$cleanQuery\""
            allProducts.filter { product ->
                product.contains(cleanQuery, ignoreCase = true)
            }
        }

        // --- DIAGNOSTIC LOGS ---
        // Open Logcat and filter for "PRODUCT_CHECK" to see these prints!
        android.util.Log.d("PRODUCT_CHECK", "Current Query: '$cleanQuery'")
        android.util.Log.d("PRODUCT_CHECK", "Filtered List Size: ${filteredList.size}")

        productAdapter.submitList(filteredList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}