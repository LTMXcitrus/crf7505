package com.lemonfactory.crf7505.config

import com.lemonfactory.crf7505.repository.ObjectifyDAO
import org.slf4j.LoggerFactory

object Config {
    private val LOGGER = LoggerFactory.getLogger(Config::class.java)

    private val objectifyDAO: ObjectifyDAO<ConfigValue> = ObjectifyDAO()

    fun getEnv(name: String, defaultValue: String): String {
        return objectifyDAO.findById<ConfigValue>(name)?.value
                ?: defaultValue
    }

    fun getEnvRequired(name: String): String {
        try {
            return objectifyDAO.findById<ConfigValue>(name)!!.value
        } catch(e: KotlinNullPointerException) {
            LOGGER.error("The property $name is required.")
            return name
        }
    }

}