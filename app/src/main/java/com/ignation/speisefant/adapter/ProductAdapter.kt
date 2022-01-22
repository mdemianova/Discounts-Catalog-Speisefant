package com.ignation.speisefant.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ignation.speisefant.R
import com.ignation.speisefant.databinding.ProductLayoutBinding
import com.ignation.speisefant.domain.Product

class ProductAdapter(private val context: Context, private val dataset: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding =
            ProductLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = dataset[position]
        with(holder) {
            with(item) {
                // add image
                binding.productTitle.text = name
                binding.productPriceNew.text = context.resources.getString(R.string.new_price, newPrice)
                binding.productDate.text = date
                binding.productPriceOld.text = context.resources.getString(R.string.old_price, oldPrice)
            }
        }
    }

    override fun getItemCount(): Int = dataset.size

    class ProductViewHolder(val binding: ProductLayoutBinding) : RecyclerView.ViewHolder(binding.root)
}