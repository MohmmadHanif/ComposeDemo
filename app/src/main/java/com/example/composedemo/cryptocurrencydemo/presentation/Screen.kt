package com.example.composedemo.cryptocurrencydemo.presentation

sealed class Screen(val route: String) {
    object CoinScreenListScreen :Screen("coin_list")
    object CoinDetailScreen :Screen("coin_list")
}