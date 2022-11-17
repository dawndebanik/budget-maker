import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import models.Config
import java.io.File

private const val CONFIG_FILE_PATH = "/var/conf/config.yml"

class ConfigLoader private constructor() {
    companion object {
        private val objectMapper = ObjectMapper(YAMLFactory())
        private var config: Config? = null
        fun getApplicationConfig(): Config {
            return when (config) {
                null -> {
                    config = objectMapper.readValue(File(CONFIG_FILE_PATH), Config::class.java)
                    config as Config
                }
                else -> config as Config
            }
        }
    }
}