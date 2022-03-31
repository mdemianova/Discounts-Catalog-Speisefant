package com.ignation.speisefant.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ignation.speisefant.databinding.ProductLayoutBinding
import com.ignation.speisefant.domain.Product

/**
 * Adapter for displaying a list of products.
 */
class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    var dataset = listOf<Product>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding =
            ProductLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = dataset[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = dataset.size

    class ProductViewHolder(val binding: ProductLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.apply {
                this.product = product
                productTitle.text = product.name
                productShop.text = product.shop
                productDetails.text = product.details
                executePendingBindings()
            }
        }
    }
}