package com.lemonfactory.covid

import com.lemonfactory.crf7505.config.Config
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory


class DispoImporter(private val transformer: Transformer, private val config: Config) {


    fun import(): List<Dispo> {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://docs.google.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
        val service = retrofit.create(SpreadsheetsService::class.java)

        return service
                .retrieveCSV(config.getEnvRequired("COVID-spreadsheet-ID"))
                .execute()
                .body()!!
                .readCSV()
                .map { fromCsvLine(it) }
                .map { transformer.transform(it) }
    }
}