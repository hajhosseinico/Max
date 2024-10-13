package com.hajhosseinico.max.presentation.home

import androidx.annotation.DrawableRes
import com.hajhosseinico.max.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int,
)
