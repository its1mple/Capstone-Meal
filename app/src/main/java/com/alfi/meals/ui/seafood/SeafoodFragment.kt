package com.alfi.meals.ui.seafood

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.alfi.meals.core.data.Resource
import com.alfi.meals.core.ui.SeafoodAdapter
import com.alfi.meals.databinding.FragmentSeafoodBinding
import org.koin.android.viewmodel.ext.android.viewModel

class SeafoodFragment : Fragment() {

    private val viewModel: SeafoodViewModel by viewModel()
    private var _binding: FragmentSeafoodBinding? = null
    private val binding get() = _binding
    private lateinit var mAdapter: SeafoodAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentSeafoodBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) initRecyclerView()
    }

    private fun initRecyclerView() {
        mAdapter = SeafoodAdapter()
        mAdapter.onItemClick = {
            val intent = Intent(requireActivity(), SeafoodDetailActivity::class.java)
            intent.putExtra(SeafoodDetailActivity.EXTRA_SEAFOOD, it)
            intent.putExtra(SeafoodDetailActivity.EXTRA_ID, it.seafoodId)
            startActivity(intent)
        }

        viewModel.seafoodList.observe(viewLifecycleOwner, { seafoods ->
            val progress = binding?.progress
            if (seafoods != null) {
                when (seafoods) {
                    is Resource.Loading -> progress?.visibility = View.VISIBLE
                    is Resource.Success -> {
                        progress?.visibility = View.GONE
                        mAdapter.setData(seafoods.data)
                    }
                    is Resource.Error -> {
                        progress?.visibility = View.GONE
                        Toast.makeText(requireContext(), "${seafoods.message}", Toast.LENGTH_LONG).show()
                    }
                }
            }

            binding?.rvSeafood?.apply {
                layoutManager = GridLayoutManager(requireContext(), 2)
                setHasFixedSize(true)
                adapter = mAdapter
            }
        })
    }

    override fun onDestroyView() {
        binding?.rvSeafood?.adapter = null
        super.onDestroyView()
        _binding = null
    }
}