import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import models.domain.Expense
import models.responses.GetExpensesResponse
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.util.EntityUtils

class SplitwiseClient(private var httpClient: HttpClient, private var apiKey: String) {

    private val objectMapper = jacksonObjectMapper()

    fun getExpenses(): List<Expense> {
        val request = HttpGet("https://secure.splitwise.com/api/v3.0/get_expenses")
        request.addHeader("Authorization", "Bearer $apiKey")
        val response = EntityUtils.toString(httpClient.execute(request).entity)
        val getExpensesResponse: GetExpensesResponse = objectMapper.readValue(response)

        return getExpensesResponse.expenses
    }
}