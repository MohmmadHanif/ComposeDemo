package com.example.composedemo.cryptocurrencydemo.domain.use_case.get_coins

import com.example.composedemo.cryptocurrencydemo.common.Resource
import com.example.composedemo.cryptocurrencydemo.data.remote.dto.toCoin
import com.example.composedemo.cryptocurrencydemo.domain.model.Coin
import com.example.composedemo.cryptocurrencydemo.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(private val repository: CoinRepository) {

    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.LOADING())
            val result = repository.getCoins().map { it.toCoin() }
            emit(Resource.SUCCESS(result))
        } catch (e: HttpException) {
            emit(Resource.ERROR(e.localizedMessage ?: "An unexpected Error Occur"))
        } catch (e: IOException) {
            emit(Resource.ERROR(e.localizedMessage ?: "Can't Reach Server. Please Check Your Internet."))
        }
    }
}