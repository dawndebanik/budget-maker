package models

data class Config (
    val apiKey: String = "",
    val appConfig: AppConfig? = null
)

data class AppConfig(
    val splitwiseV3BaseUrl: String = "",
    val getExpensesEndpoint: String = "",
    val getCurrentUserEndpoint: String = ""
)