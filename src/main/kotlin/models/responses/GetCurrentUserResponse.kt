package models.responses

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import models.domain.UserDetails

@JsonIgnoreProperties(ignoreUnknown = true)
data class GetCurrentUserResponse (
    val user: UserDetails? = null
)