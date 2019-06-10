package com.lemonfactory.crf7505

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import java.time.LocalDate

class LocalDateSerializer(): JsonSerializer<LocalDate>() {

    override fun serialize(date: LocalDate?, jsonGenerator: JsonGenerator?, serializerProvider: SerializerProvider?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}