package com.example.composedemo.cryptocurrencydemo.domain.model

import com.example.composedemo.cryptocurrencydemo.data.remote.dto.CoinDetailDto

data class CoinDetail(
    val description: String,
    val id: String,
    val isActive: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val tags: List<String>,
    val team: List<CoinDetailDto.TeamMember>,
)
