import core.clients.SplitwiseClient
import models.CategorisedUserExpenses
import org.apache.http.client.HttpClient
import org.apache.http.impl.client.HttpClients
import java.io.FileOutputStream
import java.io.OutputStream
import java.time.LocalDate
import java.time.ZoneId

fun main() {
    val httpClient: HttpClient = HttpClients.createDefault()
    val splitwise = SplitwiseClient(httpClient)
    val start = LocalDate.parse("2022-11-01").atStartOfDay(ZoneId.of("Asia/Calcutta")).toInstant()
    val end = LocalDate.parse("2022-12-01").atStartOfDay(ZoneId.of("Asia/Calcutta")).toInstant()

    val expenses = splitwise.getExpenses(start.toString(), end.toString())
    val myUserId = splitwise.getCurrentUser()?.id!!
    val categorisedUserExpenses = CategorisedUserExpenses.getFromUserExpenses(myUserId, expenses)

    fun OutputStream.writeCsv(categorisedUserExpenses: CategorisedUserExpenses) {
        val writer = bufferedWriter()
        writer.write(categorisedUserExpenses.toCsvString())
        writer.flush()
    }

    FileOutputStream("filename.csv").apply { writeCsv(categorisedUserExpenses) }
}