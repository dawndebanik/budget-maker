package utils

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import core.models.domain.Expense
import core.models.responses.GetExpensesResponse
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

internal class CategoriserUtilsTest {
    private val objectMapper = jacksonObjectMapper()
    @Test
    internal fun shouldCategoriseProperly() {
        val expenses: List<Expense> = objectMapper.readValue<GetExpensesResponse>(File("src/test/resources/expenses.json")).expenses
        val categoryMap = objectMapper.readValue<Map<String, Set<String>>>(File("src/test/resources/categories.json"))
        val waterExpense = expenses.first { expense: Expense -> expense.description.equals("Water") }
        val partyExpense = expenses.first { expense: Expense -> expense.description.equals("Party") }
        val uberExpense = expenses.first { expense: Expense -> expense.description.equals("Night Uber") }

        val categories = CategoriserUtils.categorise(expenses, categoryMap)

        assertEquals("Sustenance", categories[waterExpense])
        assertEquals("Eat Out", categories[partyExpense])
        assertEquals("Commute", categories[uberExpense])
    }
}