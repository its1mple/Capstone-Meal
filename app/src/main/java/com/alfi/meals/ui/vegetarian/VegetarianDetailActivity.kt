package com.alfi.meals.ui.vegetarian

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.alfi.meals.R
import com.alfi.meals.core.data.Resource
import com.alfi.meals.core.domain.model.Vegetarian
import com.alfi.meals.core.helper.GlideHelper
import com.alfi.meals.databinding.ActivityVegetarianDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class VegetarianDetailActivity : AppCompatActivity() {

    private val viewModel: VegetarianViewModel by viewModel()
    private val binding by lazy { ActivityVegetarianDetailBinding.inflate(layoutInflater) }

    companion object {
        const val EXTRA_ID = "vegetarian_id"
        const val EXTRA_VEGETARIAN = "extra_vegetarian"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initToolbar()
        getDetail()
        setFavorite()
    }

    private fun initToolbar() {
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        toolbar.title = getString(R.string.detail_meal)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener { finish() }
    }

    private fun getDetail() {
        val vegetarianId = intent.getStringExtra(EXTRA_ID)
        viewModel.getDetailVegetarian(vegetarianId ?: "").observe(this, { meal ->
            val progress = binding.progress
            val fab = binding.fab
            if (meal != null) {
                when(meal) {
                    is Resource.Loading -> {
                        progress.visibility = View.VISIBLE
                        fab.isEnabled = false
                    }
                    is Resource.Success -> {
                        progress.visibility = View.GONE
                        fab.isEnabled = true
                        with(binding) {
                            tvName.text     = meal.data?.get(0)?.name
                            tvCategory.text = meal.data?.get(0)?.category
                            tvCountry.text  = meal.data?.get(0)?.country
                            tvRecipe.text   = meal.data?.get(0)?.recipe

                            GlideHelper.setImageUrl(applicationContext, meal.data?.get(0)?.image ?: "", imgBackground)
                        }
                    }
                    is Resource.Error -> {
                        progress.visibility = View.GONE
                        fab.isEnabled = true
                        showToast(getString(R.string.terjadi_kesalahan))
                    }
                }
            }
        })
    }

    private fun setFavorite() {
        val vegetarian: Vegetarian = intent.getParcelableExtra<Vegetarian>(EXTRA_VEGETARIAN) as Vegetarian
        val fab = binding.fab
        var isFavorite: Boolean = vegetarian.isFavorite
        setStatusFavorite(isFavorite)
        fab.setOnClickListener {
            isFavorite = !isFavorite
            if (isFavorite) showToast(getString(R.string.added_favorite)) else showToast(getString(R.string.remove_favorite))
            viewModel.setFavorite(vegetarian, isFavorite)
            setStatusFavorite(isFavorite)
        }
    }

    private fun setStatusFavorite(status: Boolean) {
        val fab = binding.fab
        if (status) fab.setImageResource(R.drawable.favorite_enable)
        else fab.setImageResource(R.drawable.favorite_disable)
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}