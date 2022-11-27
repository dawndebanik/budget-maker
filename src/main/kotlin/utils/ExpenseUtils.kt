package utils

import core.models.domain.Expense
import core.models.domain.User

private const val CREATION_METHOD_DEBT_CONSOLIDATION_IDENTIFIER = "debt_consolidation"

class ExpenseUtils {
    companion object {
        fun getUsersShareFromExpenses(userId: String, expenses: List<Expense>): Map<Expense, Double?> {
            val nonDeletedExpenses = getNonDeletedExpenses(expenses)
            val expensesForUser = getExpensesForUserInvolved(userId, getNonPaymentExpenses(nonDeletedExpenses))
            return expensesForUser.associateWith {
                getUserFromExpense(userId, it)?.owedShare
            }
        }

        private fun getNonDeletedExpenses(expenses: List<Expense>): List<Expense> {
            return expenses.filter {
                it.deletedAt == null || it.deletedAt.isEmpty()
            }
        }

        private fun getNonPaymentExpenses(expenses: List<Expense>): List<Expense> {
            return expenses
                .filter { !it.payment }
                .filter { !it.creationMethod.equals(CREATION_METHOD_DEBT_CONSOLIDATION_IDENTIFIER) }
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