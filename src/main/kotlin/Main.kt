import org.apache.http.client.HttpClient
import org.apache.http.impl.client.HttpClients

fun main(args: Array<String>) {
    val httpClient: HttpClient = HttpClients.createDefault()
    val splitwise = SplitwiseClient(httpClient)
    println(splitwise.getExpenses(null, null, "10"))
}