package utils

import core.models.domain.Expense
import java.util.regex.Pattern

class CategoriserUtils {
    companion object {
        fun categorise(expenses: List<Expense>, categoryToTokenSetMap: Map<String, Set<String>>): Map<Expense, String?> {
            return expenses.associateWith { expense -> categorise(expense, categoryToTokenSetMap) }
        }

        private fun categorise(expense: Expense, categoryToTokenSetMap: Map<String, Set<String>>): String? {
            val tokenToCategoryMap = reverseMap(categoryToTokenSetMap).mapKeys { mapEntry -> mapEntry.key.lowercase() }
            val descriptionTokens = tokenize(expense.description)?.map { token -> token.lowercase() }
            descriptionTokens?.forEach { token ->
                if (tokenToCategoryMap.containsKey(token)) {
                    return tokenToCategoryMap[token]
                }
            }
            return expense.category?.name
        }

        private fun tokenize(description: String?): List<String>? {
            return description?.trim()?.split(Pattern.compile("\\s+"))
        }

        private fun <K, V> reverseMap(map: Map<K, Set<V>>): HashMap<V, K> {
            val reverseMap = HashMap<V, K>()
            map.forEach { (key, setOfValues) ->
                setOfValues.forEach { value -> reverseMap[value] = key }
            }
            return reverseMap
        }
    }
}