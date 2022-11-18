package utils

import models.domain.Expense

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
    }
}