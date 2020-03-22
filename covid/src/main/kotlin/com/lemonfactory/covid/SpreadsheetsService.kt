package com.lemonfactory.covid
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SpreadsheetsService {
    @GET("spreadsheets/d/{spreadsheetId}/export?format=tsv")
    fun retrieveCSV(@Path("spreadsheetId") spreadsheetId: String): Call<String>
}