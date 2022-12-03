import core.clients.SplitwiseClient
import models.CategorisedUserExpenses
import org.apache.http.client.HttpClient
import org.apache.http.impl.client.HttpClients
import java.io.FileOutputStream
import java.io.OutputStream
import java.time.LocalDate
import java.time.ZoneId

private const val INDIA_TIME_ZONE = "Asia/Calcutta"

fun main(args: Array<String>) {
    val httpClient: HttpClient = HttpClients.createDefault()
    val splitwise = SplitwiseClient(httpClient)
    val start = LocalDate.parse(args[0]).atStartOfDay(ZoneId.of(INDIA_TIME_ZONE)).toInstant()
    val end = LocalDate.parse(args[1]).atStartOfDay(ZoneId.of(INDIA_TIME_ZONE)).toInstant()

    val expenses = splitwise.getExpenses(start.toString(), end.toString())
    val myUserId = splitwise.getCurrentUser()?.id!!
    val categorisedUserExpenses = CategorisedUserExpenses.getFromUserExpenses(myUserId, expenses)

    fun OutputStream.writeCsv(categorisedUserExpenses: CategorisedUserExpenses) {
        val writer = bufferedWriter()
        writer.write(categorisedUserExpenses.toCsvString())
        writer.flush()
    }

    FileOutputStream(args[2]).apply { writeCsv(categorisedUserExpenses) }
}