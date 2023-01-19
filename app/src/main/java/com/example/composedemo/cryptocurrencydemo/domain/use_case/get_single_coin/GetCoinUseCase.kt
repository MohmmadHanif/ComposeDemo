package com.example.composedemo.cryptocurrencydemo.domain.use_case.get_single_coin

import com.example.composedemo.cryptocurrencydemo.common.Resource
import com.example.composedemo.cryptocurrencydemo.data.remote.dto.toCoinDetail
import com.example.composedemo.cryptocurrencydemo.domain.model.CoinDetail
import com.example.composedemo.cryptocurrencydemo.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(private val repository: CoinRepository) {

    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.LOADING())
            val result = repository.getCoinById(coinId = coinId).toCoinDetail()
            emit(Resource.SUCCESS(result))
        } catch (e: HttpException) {
            emit(Resource.ERROR(e.localizedMessage ?: "An unexpected Error Occur"))
        } catch (e: IOException) {
            emit(
                Resource.ERROR(e.localizedMessage ?: "Can't Reach Server. Please Check Your Internet.")
            )
        }
    }
}