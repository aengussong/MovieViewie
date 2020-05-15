package com.aengussong.movieviewie.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val name: String,
    val image: String,
    val description: String,
    val time: String
) : Parcelable