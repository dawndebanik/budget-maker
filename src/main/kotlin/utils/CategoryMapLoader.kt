package core.utils

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.readValue
import core.models.Config
import java.io.File

private const val CONFIG_FILE_PATH = "src/main/resources/categories.yml"

class CategoryMapLoader private constructor() {
    companion object {
        private val objectMapper = ObjectMapper(YAMLFactory())
        private var categoryMap: Map<String, Set<String>>? = null

        fun getConfigMap(): Map<String, Set<String>>? {
            return when (categoryMap) {
                null -> {
                    categoryMap = objectMapper.readValue(File(CONFIG_FILE_PATH))
                    categoryMap
                }
                else -> categoryMap as Map<String, Set<String>>
            }
        }
    }
}