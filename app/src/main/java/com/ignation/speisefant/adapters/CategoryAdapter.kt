package com.ignation.speisefant.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.ignation.speisefant.databinding.TypeLayoutBinding
import com.ignation.speisefant.domain.Type
import com.ignation.speisefant.domain.TypeSource

class CategoryAdapter(private val onItemClicked: (Type) -> Unit) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    val dataset = TypeSource.types

    class CategoryViewHolder(private var binding: TypeLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(type: Type) {
            binding.categoryTitle.text = type.title
            binding.categoryImage.setImageResource(type.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = TypeLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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