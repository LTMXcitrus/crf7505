package com.lemonfactory.crf7505.utils

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import java.text.ParseException
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class CustomDateDeserialize(vc: Class<*>?) : StdDeserializer<LocalDate>(vc) {

    constructor() : this(null)

    private val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")

    override fun deserialize(jsonparser: JsonParser, context: DeserializationContext): LocalDate {
        val date = jsonparser.text
        try {
            return LocalDate.parse(date, formatter)
        } catch (e: ParseException) {
            throw RuntimeException(e)
        }
    }
}