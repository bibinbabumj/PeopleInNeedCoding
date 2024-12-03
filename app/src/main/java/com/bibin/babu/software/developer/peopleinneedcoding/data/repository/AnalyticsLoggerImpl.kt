package com.bibin.babu.software.developer.peopleinneedcoding.data.repository

import android.util.Log
import com.bibin.babu.software.developer.peopleinneedcoding.domain.repository.AnalyticsLogger
import com.bibin.babu.software.developer.peopleinneedcoding.domain.repository.LogParam

class AnalyticsLoggerImpl : AnalyticsLogger {
    override fun logEvent(key: String, vararg params: LogParam<Any>) {
        val paramString = params.joinToString(", ") { "${it.key}: ${it.value}" }
        Log.d("AnalyticsLogger", "Event: $key, Params: [$paramString]")
    }
}

