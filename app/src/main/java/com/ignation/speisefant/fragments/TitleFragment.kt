package com.ignation.speisefant.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ignation.speisefant.R
import com.ignation.speisefant.adapters.CategoryAdapter
import com.ignation.speisefant.adapters.ShopAdapter
import com.ignation.speisefant.databinding.FragmentTitleBinding
import com.ignation.speisefant.repository.DefaultProductRepository
import com.ignation.speisefant.viewmodel.ProductViewModel
import com.ignation.speisefant.viewmodel.ProductViewModelFactory

class TitleFragment : Fragment() {

    private var _binding: FragmentTitleBinding? = null
    private val binding get() = _binding!!

    private val productViewModel: ProductViewModel by activityViewModels() {
        ProductViewModelFactory(DefaultProductRepository(requireActivity().application))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTitleBinding.inflate(inflater, container, false)

        productViewModel.eventNetworkError.observe(this.viewLifecycleOwner) {
            if (it) {
                Toast.makeText(activity, getString(R.string.network_error), Toast.LENGTH_SHORT)
                    .show()
                productViewModel.errorShown()
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryAdapter = CategoryAdapter {
            val action =
                TitleFragmentDirections.actionTitleFragmentToProductByTypeFragment(it.title)
            this.findNavController().navigate(action)
        }
        binding.recyclerViewCategory.adapter = categoryAdapter

        val shopAdapter = ShopAdapter {
            val action =
                TitleFragmentDirections.actionTitleFragmentToProductByShopFragment(it.title)
            this.findNavController().navigate(action)
        }
        binding.recyclerViewShop.adapter = shopAdapter

        binding.swiperefresh.setOnRefreshListener {
            productViewModel.refreshDataFromRepository()
            binding.swiperefresh.isRefreshing = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}