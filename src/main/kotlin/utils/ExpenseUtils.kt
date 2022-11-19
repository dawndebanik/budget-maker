package utils

import models.domain.Expense
import models.domain.User

class ExpenseUtils {
    companion object {
        fun getNonDeletedExpenses(expenses: List<Expense>): List<Expense> {
            return expenses.filter {
                it.deletedAt == null || it.deletedAt.isEmpty()
            }
        }

        fun getExpensesForUserInvolved(userId: String, expenses: List<Expense>): List<Expense> {
            return expenses.filter { expense ->
                expense.users?.any {
                    it.userId.equals(userId)
                } == true
            }
        }

        fun getUserFromExpense(userId: String, expense: Expense): User? {
            return expense.users?.first { it.userId.equals(userId) }
        }

        fun getUsersShareFromExpenses(userId: String, expenses: List<Expense>): Map<Expense, Double?> {
            val expensesForUser = getExpensesForUserInvolved(userId, expenses)
            return expensesForUser.associateWith {
                getUserFromExpense(userId, it)?.owedShare
            }
        }
    }
}