package models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Expense (
    val id: String,
    val description: String,
    val users: List<User>
)
