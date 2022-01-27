package com.alfi.meals.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alfi.meals.core.databinding.AdapterMealsBinding
import com.alfi.meals.core.domain.model.Seafood
import com.alfi.meals.core.helper.GlideHelper

class SeafoodAdapter : RecyclerView.Adapter<SeafoodAdapter.SeafoodViewHolder>() {

    private var seafoods = ArrayList<Seafood>()
    var onItemClick: ((Seafood) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<Seafood>?) {
        if (newListData == null) return
        seafoods.clear()
        seafoods.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeafoodViewHolder {
        val view = AdapterMealsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SeafoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: SeafoodViewHolder, position: Int) {
        holder.bind(seafoods[position])
    }

    override fun getItemCount(): Int = seafoods.size

    inner class SeafoodViewHolder(private val binding: AdapterMealsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(seafood: Seafood) {
            with(binding) {
                tvName.text = seafood.name
                GlideHelper.setImageUrl(itemView.context, seafood.image ?: "", imgMeal)
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(seafoods[adapterPosition])
            }
        }
    }
}