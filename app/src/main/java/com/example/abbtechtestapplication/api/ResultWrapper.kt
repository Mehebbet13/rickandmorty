package com.example.abbtechtestapplication.api

//sealed class ResultWrapper<out T> {
//    data class Success<out T>(val value: T, val isCachedValue: Boolean = false) : ResultWrapper<T>()
//    data class GenericError(val error: ErrorResponse) : ResultWrapper<Nothing>()
//    object NetworkError : ResultWrapper<Nothing>()
//}