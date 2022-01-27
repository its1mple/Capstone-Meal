package com.alfi.meals.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alfi.meals.favorite.databinding.FragmentFavoriteBinding
import com.google.android.material.tabs.TabLayoutMediator

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) setupViewPager()
    }

    private fun setupViewPager() {
        val vpAdapter = FavoriteViewPager(requireActivity())
        val title     = arrayOf("Seafood", "Vegetarian")
        val tabs      = binding?.tabsLayout
        val viewPager = binding?.viewPager

        viewPager?.adapter = vpAdapter
        TabLayoutMediator(tabs!!, viewPager!!) { tab, position ->
            tab.text = title[position]
        }.attach()
    }

    override fun onDestroyView() {
        binding?.viewPager?.adapter = null
        super.onDestroyView()
        _binding = null
    }
}