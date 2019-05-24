package com.lemonfactory.pegass.client.referentiel

import com.csvreader.CsvReader
import com.lemonfactory.pegass.client.model.Ul
import java.nio.charset.Charset

object UlReferentiel {

    private val referentiel: List<Ul> = load()

    fun getAllUlIds(): List<Int> {
        return referentiel.map { ul -> ul.pegassId }
    }

    fun getAllUls(): List<Ul> {
        return referentiel
    }

    fun getUlLabel(ulId: Int): String {
        return referentiel.firstOrNull { ul -> ul.pegassId == ulId }
                ?.label
                ?: "Ul inconnue"
    }

    private fun load(): List<Ul> {
        val reader = CsvReader(UlReferentiel::class.java.classLoader.getResourceAsStream("ulReferentiel.csv"), ';', Charset.forName("UTF-8"))
        reader.readHeaders()
        val ref = mutableListOf<Ul>()
        while (reader.readRecord()) {
            ref.add(Ul(reader.get("pegassId").toInt(), reader.get("label")))
        }
        return ref
    }

}
