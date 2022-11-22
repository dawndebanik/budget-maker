import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import models.domain.Expense
import models.domain.UserDetails
import models.responses.GetCurrentUserResponse
import models.responses.GetExpensesResponse
import org.apache.http.client.HttpClient
import utils.ConfigLoader
import utils.HttpUtils

private const val LIMIT_INFINITE = "0"
class SplitwiseClient(private var httpClient: HttpClient) {

    private val objectMapper = jacksonObjectMapper()
    private val config = ConfigLoader.getApplicationConfig()

    fun getExpenses(fromDate: String? = null, toDate: String? = null, limit: String? = LIMIT_INFINITE): List<Expense> {
        val params = getParams(fromDate, toDate, limit)
        val response = HttpUtils.performGetCall(httpClient, config.appConfig?.getExpensesEndpoint, params)
        val getExpensesResponse: GetExpensesResponse = objectMapper.readValue(response)

        return getExpensesResponse.expenses
    }

    fun getCurrentUser(): UserDetails? {
        val response = HttpUtils.performGetCall(httpClient, config.appConfig?.getCurrentUserEndpoint)
        val getCurrentUserResponse: GetCurrentUserResponse = objectMapper.readValue(response)

        return getCurrentUserResponse.user
    }

    private fun getParams(fromDate: String?, toDate: String?, limit: String?): Map<String, String> {
        val params = HashMap<String, String>()
        fromDate?.let { params.put("dated_after", it) }
        toDate?.let { params.put("dated_before", it) }
        limit?.let { params.put("limit", it) }
        return params
    }
}
