import org.apache.http.client.HttpClient
import org.apache.http.impl.client.HttpClients

fun main(args: Array<String>) {
    val httpClient: HttpClient = HttpClients.createDefault()
    val config = ConfigLoader.getApplicationConfig()
    val splitwise = SplitwiseClient(httpClient, config.apiKey)
    println(splitwise.getExpenses())
}