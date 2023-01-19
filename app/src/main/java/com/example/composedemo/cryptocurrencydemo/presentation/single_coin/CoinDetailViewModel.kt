package com.example.composedemo.cryptocurrencydemo.presentation.single_coin

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composedemo.cryptocurrencydemo.common.Constant
import com.example.composedemo.cryptocurrencydemo.common.Resource
import com.example.composedemo.cryptocurrencydemo.domain.model.CoinDetail
import com.example.composedemo.cryptocurrencydemo.domain.use_case.get_single_coin.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val useCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CoinListState())
    val state: State<CoinListState> = _state

    init {
        savedStateHandle.get<String>(Constant.PARAM_COIN_ID)?.let {
            getCoins(it)
        }
    }

    private fun getCoins(coinId: String) {
        useCase.invoke(coinId).onEach {
            when (it) {
                is Resource.ERROR -> _state.value =
                    CoinListState(error = it.message ?: "unExpected Error")

                is Resource.LOADING -> _state.value = CoinListState(isLoading = true)

                is Resource.SUCCESS -> _state.value = CoinListState(coin = it.data)

            }
        }.launchIn(viewModelScope)
    }
}

data class CoinListState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)