package models.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class User (
    @JsonProperty("user_id") val userId: String? = null,
    @JsonProperty("paid_share") val paidShare: Double? = null,
    @JsonProperty("owed_share") val owedShare: Double? = null,
    @JsonProperty("net_balance") val netBalance: Double? = null
)