package core.models.responses

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import core.models.domain.UserDetails

@JsonIgnoreProperties(ignoreUnknown = true)
data class GetCurrentUserResponse (
    val user: UserDetails? = null
)