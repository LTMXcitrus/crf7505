package com.lemonfactory.pegass.client

import java.io.BufferedReader
import java.io.InputStreamReader


fun getResourceFileAsString(fileName: String): String? {
    val classLoader = ClassLoader.getSystemClassLoader()
    val `is` = classLoader.getResourceAsStream(fileName)
    if (`is` != null) {
        val reader = BufferedReader(InputStreamReader(`is`))
        return reader.lineSequence().joinToString { "\n" }
    }
    return null
}