package com.github.marinamadaras.gesturesinide.config

import java.util.*

class PropertiesConfig private constructor() {
    companion object {
        private const val CONFIG_FILE_PATH = "application.properties"
        private val configFileStream = PropertiesConfig::class.java.classLoader.getResourceAsStream(CONFIG_FILE_PATH)

        private val properties: Properties by lazy {
            loadProperties()
        }

        /**
         * Loads the configuration file and returns the Properties object that holds the properties
         */
        private fun loadProperties(): Properties {
            val props = Properties()
            props.load(configFileStream)
            return props
        }

        /**
         * Gets the value of the property with the given key
         *
         * @param key the key of the property
         */
        fun get(key: String): String = properties.getProperty(key)

        /**
         * Gets the value of the property with the given key as a boolean
         *
         * @param key the key of the property
         * @param defaultValue the default value to return if the property is not found
         */
        fun getBoolean(key: String, defaultValue: Boolean = false): Boolean =
            properties.getProperty(key)?.toBoolean() ?: defaultValue

        /**
         * Gets the value of the property with the given key as a double
         *
         * @param key the key of the property
         * @param defaultValue the default value to return if the property is not found
         */
        fun getDouble(key: String, defaultValue: Double = 0.7): Double =
            properties.getProperty(key)?.toDouble() ?: defaultValue
    }
}