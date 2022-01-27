package com.alfi.meals.favorite

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class FavoriteViewPager(fragment: FragmentActivity): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> FavoriteSeafoodFragment()
            1 -> FavoriteVegetarianFragment()
            else -> Fragment()
        }
    }
}