package com.bibin.babu.software.developer.peopleinneedcoding.util

sealed class ResultErrorHandling<out T> {
    data class Success<out T>(val value: T) : ResultErrorHandling<T>()
    data class Failure(val message: String, val error: Throwable? = null) : ResultErrorHandling<Nothing>()
    data object Loading : ResultErrorHandling<Nothing>()
}