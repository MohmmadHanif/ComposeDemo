package com.example.composedemo.cryptocurrencydemo.data.remote.dto


import com.example.composedemo.cryptocurrencydemo.domain.model.Coin
import com.google.gson.annotations.SerializedName

data class CoinsDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("rank")
    val rank: Int,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("type")
    val type: String
)


fun CoinsDto.toCoin(): Coin {
    return Coin(id, isActive, name, rank, symbol)
}