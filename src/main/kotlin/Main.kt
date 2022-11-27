import core.clients.SplitwiseClient
import org.apache.http.client.HttpClient
import org.apache.http.impl.client.HttpClients
import utils.ExpenseUtils
import java.time.LocalDate
import java.time.ZoneId

fun main() {
    val httpClient: HttpClient = HttpClients.createDefault()
    val splitwise = SplitwiseClient(httpClient)
    val start = LocalDate.parse("2022-11-01").atStartOfDay(ZoneId.of("Asia/Calcutta")).toInstant()
    val end = LocalDate.parse("2022-12-01").atStartOfDay(ZoneId.of("Asia/Calcutta")).toInstant()

    val expenses = splitwise.getExpenses(start.toString(), end.toString())
    val myUserId = splitwise.getCurrentUser()?.id!!
    val usersShare = ExpenseUtils.getUsersShareFromExpenses(myUserId, expenses)
    usersShare.forEach { (expense, share) ->
        println("${expense.description} --- ${expense.date} --- $share")
    }
}