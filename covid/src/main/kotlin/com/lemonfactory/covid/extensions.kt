package com.lemonfactory.covid

fun String.readCSV(): List<List<String>> {
    return this
            .split("\n")
            .filter { !it.startsWith("Horodateur") }
            .map { it.split("\t") }

}