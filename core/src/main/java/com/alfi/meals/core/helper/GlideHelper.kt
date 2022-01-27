package com.alfi.meals.core.helper

import android.content.Context
import android.widget.ImageView
import com.alfi.meals.core.R
import com.bumptech.glide.Glide

object GlideHelper {

    fun setImageUrl(context: Context, urlImage: String, imageView: ImageView?) {
        Glide.with(context)
            .load(urlImage)
            .placeholder(R.drawable.ic_error)
            .error(R.drawable.ic_error)
            .into(imageView as ImageView)
    }

}