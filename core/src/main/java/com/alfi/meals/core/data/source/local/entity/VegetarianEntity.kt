package com.alfi.meals.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity
data class VegetarianEntity(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    var id: Int? = 0,

    var vegetarianId: String? = "",

    var name: String? = "",

    var image: String? = "",

    var isFavorite: Boolean = false
): Parcelable
