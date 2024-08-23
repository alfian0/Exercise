package com.example.dicodingsubmission1

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val title: String,
    val description: String,
    val image: String
): Parcelable
