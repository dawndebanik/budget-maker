package models

import Categoriser
import core.models.domain.Expense
import utils.ExpenseUtils

class CategorisedUserExpenses private constructor(private val categorisedUserExpenseList: List<CategorisedUserExpense>) {
    companion object {
        fun getFromUserExpenses(myUserId: String, expenses: List<Expense>, categoriser: Categoriser = Categoriser()): CategorisedUserExpenses {
            val usersShare = ExpenseUtils.getUsersShareFromExpenses(myUserId, expenses)
            val categories = categoriser.categorise(usersShare.keys.toList())

            val categorisedUserExpenseList = usersShare.map { expenseToShare ->
                CategorisedUserExpense(expenseToShare.key, expenseToShare.value, categories[expenseToShare.key])
            }
                .toList()
                .sortedWith(
                    compareBy<CategorisedUserExpense> { it.category }.thenByDescending { it.userShare }
                )

            return CategorisedUserExpenses(categorisedUserExpenseList)
        }
    }

    fun toCsvString(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("Expense,Category,Share\n")
        this.categorisedUserExpenseList.forEach { stringBuilder.append(it.toCsvRow() + "\n") }

        return stringBuilder.toString()
    }

    class CategorisedUserExpense(val expense: Expense, val userShare: Double?, val category: String?) {
        fun toCsvRow(): String {
            return "${expense.description},$category,$userShare"
        }
    }
}