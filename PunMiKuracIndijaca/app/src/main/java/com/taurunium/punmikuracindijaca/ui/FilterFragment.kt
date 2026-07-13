package com.taurunium.punmikuracindijaca.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.taurunium.punmikuracindijaca.R
import com.taurunium.punmikuracindijaca.databinding.FragmentFilterBinding
import com.taurunium.punmikuracindijaca.viewmodels.ProductSharedViewModel


class FilterFragment : Fragment(R.layout.fragment_filter) {

    private val sharedViewModel: ProductSharedViewModel by activityViewModels()
    private var _binding: FragmentFilterBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentFilterBinding.bind(view)
        binding.quickFiltersGroup.setOnCheckedStateChangeListener { group, checkedIds ->
            if (checkedIds.isNotEmpty()) {
                val chip = group.findViewById<com.google.android.material.chip.Chip>(checkedIds.first())
                sharedViewModel.updateFilter(chip.text.toString())
            } else {
                sharedViewModel.updateFilter("") // Clear filter if unchecked
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}