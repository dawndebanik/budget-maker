package models.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Expense (
    val id: String? = null,
    val description: String? = null,
    val users: List<User>? = null,
    val date: String? = null,
    val category: Category? = null,
    @JsonProperty("deleted_at") val deletedAt: String? = null
)
