package com.alfi.meals.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alfi.meals.core.databinding.AdapterMealsBinding
import com.alfi.meals.core.domain.model.Vegetarian
import com.alfi.meals.core.helper.GlideHelper

class VegetarianAdapter : RecyclerView.Adapter<VegetarianAdapter.VegetarianViewHolder>() {

    private var vegetarians = ArrayList<Vegetarian>()
    var onItemClick: ((Vegetarian) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<Vegetarian>?) {
        if (newListData == null) return
        vegetarians.clear()
        vegetarians.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VegetarianViewHolder {
        val view = AdapterMealsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VegetarianViewHolder(view)
    }

    override fun onBindViewHolder(holder: VegetarianViewHolder, position: Int) {
        holder.binding(vegetarians[position])
    }

    override fun getItemCount(): Int = vegetarians.size

    inner class VegetarianViewHolder(private val binding: AdapterMealsBinding): RecyclerView.ViewHolder(binding.root) {
        fun binding(vegetarian: Vegetarian) {
            with(binding) {
                tvName.text = vegetarian.name
                GlideHelper.setImageUrl(itemView.context, vegetarian.image ?: "", imgMeal)
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(vegetarians[adapterPosition])
            }
        }
    }
}