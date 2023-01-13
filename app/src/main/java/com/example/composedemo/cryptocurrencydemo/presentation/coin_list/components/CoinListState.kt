package com.example.composedemo.cryptocurrencydemo.presentation.coin_list.components

import com.example.composedemo.cryptocurrencydemo.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coin: List<Coin> = emptyList(),
    val error: String = ""
)