package com.bibin.babu.software.developer.peopleinneedcoding.domain.repository

interface AnalyticsLogger {
    fun logEvent(key: String, vararg params: LogParam<Any>)
}

data class LogParam<T>(
    val key: String,
    val value: T

)