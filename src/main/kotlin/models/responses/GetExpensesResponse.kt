package models.responses

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import models.Expense

@JsonIgnoreProperties(ignoreUnknown = true)
data class GetExpensesResponse (
    val expenses: List<Expense>
)