package utils

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import core.models.domain.Expense
import core.models.responses.GetExpensesResponse
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

internal class ExpenseUtilsTest {
    private val objectMapper = jacksonObjectMapper()

    @Test
    fun shouldRemoveDeletedExpensesProperly() {
        val expenses: List<Expense> = objectMapper.readValue<GetExpensesResponse>(File("src/test/resources/expenses.json")).expenses
        val finalExpenses = expenses.takeLast(3)
        val sharesForUser = ExpenseUtils.getUsersShareFromExpenses("27845780", expenses)
        val nameToShare = sharesForUser.mapKeysTo(HashMap<String?, Double?>()) { entry -> entry.key.description }

        assertEquals(4, nameToShare.size)
        assertEquals(107.0, sharesForUser[finalExpenses[0]])
        assertEquals(434.0, sharesForUser[finalExpenses[1]])
        assertEquals(1752.5, sharesForUser[finalExpenses[2]])
    }
}