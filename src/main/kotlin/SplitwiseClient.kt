import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import models.domain.Expense
import models.responses.GetExpensesResponse
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.util.EntityUtils

private const val SPLITWISE_V3_BASE_URL = "https://secure.splitwise.com/api/v3.0"

class SplitwiseClient(private var httpClient: HttpClient, private var apiKey: String) {

    private val objectMapper = jacksonObjectMapper()

    fun getExpenses(): List<Expense> {
        val response = performGetCall("get_expenses")
        val getExpensesResponse: GetExpensesResponse = objectMapper.readValue(response)

        return getExpensesResponse.expenses
    }

    private fun performGetCall(endpoint: String): String {
        val request = HttpGet("$SPLITWISE_V3_BASE_URL/$endpoint").addAuthHeader(apiKey)
        return EntityUtils.toString(httpClient.execute(request).entity)
    }
}

private fun HttpGet.addAuthHeader(apiKey: String): HttpGet {
    this.addHeader("Authorization", "Bearer $apiKey")
    return this
}
