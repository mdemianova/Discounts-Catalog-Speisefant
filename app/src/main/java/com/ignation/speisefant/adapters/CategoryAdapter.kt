package com.ignation.speisefant.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ignation.speisefant.databinding.CategoryLayoutBinding
import com.ignation.speisefant.domain.Category
import com.ignation.speisefant.domain.CategorySource

class CategoryAdapter(private val onItemClicked: (Category) -> Unit) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    val dataset = CategorySource.categories

    class CategoryViewHolder(private var binding: CategoryLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category) {
            binding.categoryTitle.text = category.title
            binding.categoryImage.setImageResource(category.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = CategoryLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val currentCategory = dataset[position]
        holder.itemView.setOnClickListener {
            onItemClicked(currentCategory)
        }
        holder.bind(currentCategory)
    }

    override fun getItemCount(): Int = dataset.size
}