package com.example.composedemo.cryptocurrencydemo.presentation.coin_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composedemo.cryptocurrencydemo.common.Resource
import com.example.composedemo.cryptocurrencydemo.domain.use_case.get_coins.GetCoinsUseCase
import com.example.composedemo.cryptocurrencydemo.presentation.coin_list.components.CoinListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(private val useCase: GetCoinsUseCase) : ViewModel() {

    private val _state = mutableStateOf(CoinListState())
    val state: State<CoinListState> = _state

    init {
        getCoins()
    }

     fun getCoins() {
        useCase().onEach {
            when (it) {
                is Resource.ERROR -> _state.value =
                    CoinListState(error = it.message ?: "unExpected Error")

                is Resource.LOADING -> _state.value = CoinListState(isLoading = true)

                is Resource.SUCCESS -> _state.value = CoinListState(coin = it.data!!)

            }
        }.launchIn(viewModelScope)
    }
}

