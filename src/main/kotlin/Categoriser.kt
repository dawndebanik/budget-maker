import core.models.domain.Expense
import core.utils.CategoryMapLoader
import utils.CategoriserUtils

class Categoriser {
    fun categorise(expenses: List<Expense>): Map<Expense, String?> {
        val categoryMap = CategoryMapLoader.getConfigMap()
        return CategoriserUtils.categorise(expenses, categoryMap!!)
    }
}