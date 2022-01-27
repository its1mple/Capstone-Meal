package com.alfi.meals.ui.vegetarian

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.alfi.meals.core.data.Resource
import com.alfi.meals.core.ui.VegetarianAdapter
import com.alfi.meals.databinding.FragmentVegetarianBinding
import org.koin.android.viewmodel.ext.android.viewModel

class VegetarianFragment : Fragment() {

    private val viewModel: VegetarianViewModel by viewModel()
    private var _binding: FragmentVegetarianBinding? = null
    private val binding get() = _binding
    private lateinit var mAdapter: VegetarianAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentVegetarianBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) initRecyclerView()
    }

    private fun initRecyclerView() {
        mAdapter = VegetarianAdapter()
        mAdapter.onItemClick = {
            val intent = Intent(requireActivity(), VegetarianDetailActivity::class.java)
            intent.putExtra(VegetarianDetailActivity.EXTRA_VEGETARIAN, it)
            intent.putExtra(VegetarianDetailActivity.EXTRA_ID, it.vegetarianId)
            startActivity(intent)
        }

        viewModel.vegetarianList.observe(viewLifecycleOwner, { vegetarians ->
            val progress = binding?.progress
            if (vegetarians != null) {
                when (vegetarians) {
                    is Resource.Loading -> progress?.visibility = View.VISIBLE
                    is Resource.Success -> {
                        progress?.visibility = View.GONE
                        mAdapter.setData(vegetarians.data)
                    }
                    is Resource.Error -> {
                        progress?.visibility = View.GONE
                        Toast.makeText(requireContext(), "${vegetarians.message}", Toast.LENGTH_LONG).show()
                    }
                }

                binding?.rvVegetarian?.apply {
                    layoutManager = GridLayoutManager(requireContext(), 2)
                    setHasFixedSize(true)
                    adapter = mAdapter
                }
            }
        })
    }

    override fun onDestroyView() {
        binding?.rvVegetarian?.adapter = null
        super.onDestroyView()
        _binding = null
    }
}