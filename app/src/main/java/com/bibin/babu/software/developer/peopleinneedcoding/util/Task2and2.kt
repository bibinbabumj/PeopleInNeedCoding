package com.bibin.babu.software.developer.peopleinneedcoding.util

fun main() {


    println(SafeDivide(10, 5))
    println("Helloo".vowelCount())
}

fun SafeDivide(a: Int?, b: Int?): Int? {
    return if (a != null && b != null) {
        if (b == 0) null else a / b
    } else {
        null
    }
}

fun String.vowelCount(): Int {
    val vowels = "aeiouAEIOU"
    return this.count { it in vowels }
}