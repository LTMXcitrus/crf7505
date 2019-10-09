package com.lemonfactory.crf7505.config

import com.lemonfactory.crf7505.repository.ObjectifyDAO

object Config {

    private val objectifyDAO: ObjectifyDAO<ConfigValue> = ObjectifyDAO()

    fun getEnv(name: String, defaultValue: String): String {
        return objectifyDAO.findById<ConfigValue>(name)?.value
                ?: defaultValue
    }

    fun getEnvRequired(name: String): String {
        return objectifyDAO.findById<ConfigValue>(name)!!.value
    }

}