package com.lemonfactory.pegass.client.referentiel

import com.csvreader.CsvReader
import com.lemonfactory.pegass.client.model.Ul

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
        val reader = CsvReader(UlReferentiel::class.java.getResource("/ulReferentiel.csv").path, ';')
        reader.readHeaders()
        val ref = mutableListOf<Ul>()
        while (reader.readRecord()) {
            ref.add(Ul(reader.get("pegassId").toInt(), reader.get("label")))
        }
        return ref
    }

}
