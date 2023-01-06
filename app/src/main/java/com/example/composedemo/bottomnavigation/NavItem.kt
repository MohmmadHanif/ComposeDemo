package com.example.composedemo.bottomnavigation

import androidx.annotation.DrawableRes
import com.example.composedemo.R

sealed class BottomNavItem(
    val route: String,
    @DrawableRes val icon: Int,
    val name: String
) {
    object Home: BottomNavItem("home", R.drawable.home,"Home")
    object Feed: BottomNavItem("feed", R.drawable.feed,"Feed")
    object Setting: BottomNavItem("setting", R.drawable.settings,"Setting")
}