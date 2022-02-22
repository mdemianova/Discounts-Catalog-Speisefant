package com.ignation.speisefant.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.ignation.speisefant.adapters.ProductAdapter
import com.ignation.speisefant.databinding.FragmentProductByTypeBinding
import com.ignation.speisefant.repository.DefaultProductRepository
import com.ignation.speisefant.viewmodel.ProductViewModel
import com.ignation.speisefant.viewmodel.ProductViewModelFactory

class ProductByTypeFragment : Fragment() {

    private var _binding: FragmentProductByTypeBinding? = null
    private val binding get() = _binding!!

    private val productViewModel: ProductViewModel by activityViewModels() {
        ProductViewModelFactory(DefaultProductRepository(requireActivity().application))
    }

    private val navigationArgs: ProductByTypeFragmentArgs by navArgs()
    private lateinit var type: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductByTypeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        type = navigationArgs.type
        (activity as AppCompatActivity).supportActionBar?.title = type

        val adapter = ProductAdapter()
        binding.recyclerView.adapter = adapter

        productViewModel.filteredByType(type, productViewModel.productsOrderByShop).observe(this.viewLifecycleOwner) {
            it.let {
                adapter.dataset = it
            }
            if (adapter.dataset.isEmpty()) {
                binding.recyclerView.visibility = View.GONE
                binding.emptyTitle.visibility = View.VISIBLE
                binding.emptyText.visibility = View.VISIBLE
            } else {
                binding.recyclerView.visibility = View.VISIBLE
                binding.emptyTitle.visibility = View.GONE
                binding.emptyText.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}