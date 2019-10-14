package com.lemonfactory.crf7505.config

import com.lemonfactory.crf7505.repository.ObjectifyDAO
import org.slf4j.LoggerFactory

class Config(private val objectifyDAO: ObjectifyDAO<ConfigValue>) {
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
        if(localStorage.containsKey(name)) {
            return localStorage[name]!!
        }
        return requiredProperty(name)
    }

    private fun requiredProperty(name: String): String {
        try {
            val configValue = objectifyDAO.findById<ConfigValue>(name)
            if(configValue == null || configValue.value.isEmpty()) {
                LOGGER.info("Config value not stored in base")
                return propertyNotFound(name)
            }
            return configValue.value
        } catch (e: KotlinNullPointerException) {
            return propertyNotFound(name)
        }
    }

    private fun propertyNotFound(name: String): String {
        LOGGER.error("The property $name is required.")
        return name
    }

}
