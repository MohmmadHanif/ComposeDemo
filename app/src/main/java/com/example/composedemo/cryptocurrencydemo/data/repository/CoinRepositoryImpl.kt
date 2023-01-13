package com.example.composedemo.cryptocurrencydemo.data.repository

import com.example.composedemo.cryptocurrencydemo.data.remote.CoinPaprikaApi
import com.example.composedemo.cryptocurrencydemo.data.remote.dto.CoinDetailDto
import com.example.composedemo.cryptocurrencydemo.data.remote.dto.CoinsDto
import com.example.composedemo.cryptocurrencydemo.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(private val api: CoinPaprikaApi) : CoinRepository {
    override suspend fun getCoins(): List<CoinsDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}