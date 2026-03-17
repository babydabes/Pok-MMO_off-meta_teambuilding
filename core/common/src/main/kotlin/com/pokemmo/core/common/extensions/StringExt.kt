package com.pokemmo.core.common.extensions

fun String.capitalizeWords(): String =
    split("-", " ").joinToString(" ") { word ->
        word.replaceFirstChar { it.uppercase() }
    }

fun String.toFtsQuery(): String {
    val cleaned = trim().replace(Regex("[^a-zA-Z0-9 ]"), "")
    return if (cleaned.isBlank()) "" else "$cleaned*"
}
