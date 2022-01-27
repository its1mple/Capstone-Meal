package com.alfi.meals.favorite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.alfi.meals.core.ui.SeafoodAdapter
import com.alfi.meals.favorite.databinding.FragmentFavoriteSeafoodBinding
import com.alfi.meals.favorite.di.favoriteModule
import com.alfi.meals.ui.seafood.SeafoodDetailActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteSeafoodFragment : Fragment() {

    private val viewModel: FavoriteViewModel by viewModel()

    private var _binding: FragmentFavoriteSeafoodBinding? = null
    private val binding get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentFavoriteSeafoodBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            loadKoinModules(favoriteModule)
            showAllFavorite()
        }
    }

    private fun showAllFavorite() {
        val mAdapter = SeafoodAdapter()
        mAdapter.onItemClick = {
            val intent = Intent(requireActivity(), SeafoodDetailActivity::class.java)
            intent.putExtra(SeafoodDetailActivity.EXTRA_SEAFOOD, it)
            intent.putExtra(SeafoodDetailActivity.EXTRA_ID, it.seafoodId)
            startActivity(intent)
        }

        viewModel.favoriteSeafood.observe(viewLifecycleOwner, { seafoods ->
            with(binding!!) {
                if (seafoods.isNullOrEmpty()) {
                    rvFavorite.visibility  = View.GONE
                    lottieEmpty.visibility = View.VISIBLE
                    tvEmpty.visibility     = View.VISIBLE
                } else {
                    mAdapter.setData(seafoods)
                    rvFavorite.visibility  = View.VISIBLE
                    lottieEmpty.visibility = View.GONE
                    tvEmpty.visibility     = View.GONE
                }

                rvFavorite.layoutManager = GridLayoutManager(requireContext(), 2)
                rvFavorite.setHasFixedSize(true)
                rvFavorite.adapter = mAdapter
            }
        })
    }

    override fun onDestroyView() {
        binding?.rvFavorite?.adapter = null
        super.onDestroyView()
        _binding = null
    }
}