package com.example.composedemo.cryptocurrencydemo.domain.repository

import com.example.composedemo.cryptocurrencydemo.data.remote.dto.CoinDetailDto
import com.example.composedemo.cryptocurrencydemo.data.remote.dto.CoinsDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinsDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto
}