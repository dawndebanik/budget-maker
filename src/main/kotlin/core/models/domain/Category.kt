package core.models.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Category (
    val id: String? = null,
    val name: String? = null
)