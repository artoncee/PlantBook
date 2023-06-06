package com.example.plantbook.utils

fun <T> MutableList<T>.replaceWith(value: T, size: Int) {
    clear()
    for (i in 0 until size) add(value)
}

fun <T> MutableList<T>.replaceWith(items: List<T>) {
    clear()
    addAll(items)
}