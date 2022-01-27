package com.alfi.meals.favorite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.alfi.meals.core.ui.VegetarianAdapter
import com.alfi.meals.favorite.databinding.FragmentFavoriteVegetarianBinding
import com.alfi.meals.favorite.di.favoriteModule
import com.alfi.meals.ui.vegetarian.VegetarianDetailActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteVegetarianFragment : Fragment() {

    private val viewModel: FavoriteViewModel by viewModel()

    private var _binding: FragmentFavoriteVegetarianBinding? = null
    private val binding get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentFavoriteVegetarianBinding.inflate(inflater, container, false)
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
        val mAdapter = VegetarianAdapter()
        mAdapter.onItemClick = {
            val intent = Intent(requireActivity(), VegetarianDetailActivity::class.java)
            intent.putExtra(VegetarianDetailActivity.EXTRA_VEGETARIAN, it)
            intent.putExtra(VegetarianDetailActivity.EXTRA_ID, it.vegetarianId)
            startActivity(intent)
        }

        viewModel.favoriteVegetarian.observe(viewLifecycleOwner, { vegetarians ->
            with(binding!!) {
                if (vegetarians.isNullOrEmpty()) {
                    rvFavorite.visibility  = View.GONE
                    lottieEmpty.visibility = View.VISIBLE
                    tvEmpty.visibility     = View.VISIBLE
                } else {
                    mAdapter.setData(vegetarians)
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