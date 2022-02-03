package com.ignation.speisefant.title

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ignation.speisefant.databinding.ShopItemLayoutBinding
import com.ignation.speisefant.domain.Shop
import com.ignation.speisefant.domain.ShopSource

class ShopAdapter() : RecyclerView.Adapter<ShopAdapter.ShopViewHolder>() {

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
        val item = dataset[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}