package com.lemonfactory.crf7505.config

import com.lemonfactory.crf7505.repository.ObjectifyDAO
import org.slf4j.LoggerFactory

class Config(val objectifyDAO: ObjectifyDAO<ConfigValue>) {
    private val LOGGER = LoggerFactory.getLogger(Config::class.java)

    private val localStorage = mutableMapOf<String, String>()

    fun getEnv(name: String, defaultValue: String): String {
        if(localStorage[name].isNullOrEmpty()) {
            return objectifyDAO.findById<ConfigValue>(name)?.value
                    ?: defaultValue
        }
        return localStorage[name]!!
    }

    fun getEnvRequired(name: String): String {
        if(localStorage[name].isNullOrEmpty()) {
            return requiredProperty(name)
        }
        return localStorage[name]!!
    }

    private fun requiredProperty(name: String): String {
        return try {
            objectifyDAO.findById<ConfigValue>(name)!!.value
        } catch (e: KotlinNullPointerException) {
            LOGGER.error("The property $name is required.")
            name
        }
    }

}