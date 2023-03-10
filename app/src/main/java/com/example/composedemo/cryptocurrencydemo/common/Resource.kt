package com.example.composedemo.cryptocurrencydemo.common

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class SUCCESS<T>(data: T) : Resource<T>(data)
    class ERROR<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class LOADING<T>() : Resource<T>()
}
