package utils

import models.domain.Expense
import models.domain.User

class ExpenseUtils {
    companion object {
        fun getUsersShareFromExpenses(userId: String, expenses: List<Expense>): Map<Expense, Double?> {
            val expensesForUser = getExpensesForUserInvolved(userId, getNonDeletedExpenses(expenses))
            return expensesForUser.associateWith {
                getUserFromExpense(userId, it)?.owedShare
            }
        }

        private fun getNonDeletedExpenses(expenses: List<Expense>): List<Expense> {
            return expenses.filter {
                it.deletedAt == null || it.deletedAt.isEmpty()
            }
        }

        private fun getExpensesForUserInvolved(userId: String, expenses: List<Expense>): List<Expense> {
            return expenses.filter { expense ->
                expense.users?.any {
                    it.userId.equals(userId)
                } == true
            }
        }

        private fun getUserFromExpense(userId: String, expense: Expense): User? {
            return expense.users?.first { it.userId.equals(userId) }
        }
    }
}