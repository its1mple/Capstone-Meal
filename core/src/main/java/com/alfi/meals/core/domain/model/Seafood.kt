package com.alfi.meals.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Seafood(
    var id: Int? = 0,
    var seafoodId: String? = "",
    var name: String? = "",
    var image: String? = "",
    var isFavorite: Boolean = false
) : Parcelable