package com.ignation.speisefant.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ignation.speisefant.databinding.ShopItemLayoutBinding
import com.ignation.speisefant.domain.Shop
import com.ignation.speisefant.domain.ShopSource

/**
 * Adapter for displaying a grid of shops on the title screen.
 */
class ShopAdapter(private val onItemClicked: (Shop) -> Unit) :
    RecyclerView.Adapter<ShopAdapter.ShopViewHolder>() {

    val dataset = ShopSource.shops

    class ShopViewHolder(private var binding: ShopItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(shop: Shop) {
            binding.apply {
                shopTitle.text = shop.title
                shopImage.setImageResource(shop.imageResId)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        val binding =
            ShopItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShopViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        val currentShop = dataset[position]
        holder.itemView.setOnClickListener {
            onItemClicked(currentShop)
        }
        holder.bind(currentShop)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}