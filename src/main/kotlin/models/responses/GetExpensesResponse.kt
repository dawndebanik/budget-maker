package models.responses

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import models.domain.Expense
import java.util.*

@JsonIgnoreProperties(ignoreUnknown = true)
data class GetExpensesResponse (
    val expenses: List<Expense> = Collections.emptyList()
)