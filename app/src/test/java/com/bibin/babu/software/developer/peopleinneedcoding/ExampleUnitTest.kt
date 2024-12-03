package com.bibin.babu.software.developer.peopleinneedcoding

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    fun safeDivide(a: Int?, b: Int?): Int? {
        return if (a != null && b != null) {
            if (b == 0) null else a / b // Handle division by zero safely
        } else {
            null
        }
    }
}