package com.ignation.speisefant.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.ignation.speisefant.databinding.FragmentProductByTabsBinding

class ProductByTabsFragment : Fragment() {

    private var _binding: FragmentProductByTabsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(this, ProductViewModelFactory(activity.application))
            .get(ProductViewModel::class.java)
    }

    private val navigationArgs: ProductByTabsFragmentArgs by navArgs()
    private lateinit var filterType: String
    private var isShop: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProductByTabsBinding.inflate(layoutInflater)

        filterType = navigationArgs.filterTitle
        isShop = navigationArgs.isShop
        (activity as AppCompatActivity).supportActionBar?.title = filterType

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ProductAdapter()
        binding.recyclerView.adapter = adapter

        if (isShop) {
            viewModel.productsByShop(filterType).observe(this.viewLifecycleOwner) { list ->
                adapter.dataset = list
            }
        }

        binding.recyclerView.setHasFixedSize(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}