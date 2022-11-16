import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.util.EntityUtils

class SplitwiseClient(private var httpClient: HttpClient, private var apiKey: String) {

    fun getExpenses(): String? {
        val request = HttpGet("https://secure.splitwise.com/api/v3.0/get_expenses")
        request.addHeader("Authorization", "Bearer $apiKey")
        return EntityUtils.toString(httpClient.execute(request).entity)
    }
}