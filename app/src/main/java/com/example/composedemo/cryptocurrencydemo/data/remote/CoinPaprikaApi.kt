package com.example.composedemo.cryptocurrencydemo.data.remote

import com.example.composedemo.cryptocurrencydemo.data.remote.dto.CoinDetailDto
import com.example.composedemo.cryptocurrencydemo.data.remote.dto.CoinsDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {

    @GET("coins")
    suspend fun getCoins(): List<CoinsDto>

    @GET("coins/{coinId}")
    suspend fun getCoinById(
        @Path("coinId") coinId: String
    ): CoinDetailDto

}