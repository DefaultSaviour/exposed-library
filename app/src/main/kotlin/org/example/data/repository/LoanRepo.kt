package org.example.data.repository
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.or
import org.example.data.tables.Book_Copies
import org.example.data.tables.Books
import org.example.data.tables.Customers
import org.example.domain.models.CustomerSearch
import org.example.domain.models.LoanWithDetails
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.isNull
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDate

class LoanRepo {


    private val loansJoin = Book_Copies
        .innerJoin(Books)
        .innerJoin(Customers)


    private fun mapRowToLoan(row: ResultRow) = LoanWithDetails(
        copyId = row[Book_Copies.copyId],
        isbn = row[Books.isbn],
        title = row[Books.title],
        customerId = row[Customers.customerId],
        firstName = row[Customers.firstName],
        lastName = row[Customers.lastName],
        dueDate = row[Book_Copies.dueDate]
    )


    fun getCheckedOutBooks(): List<LoanWithDetails> = transaction {
        loansJoin
            .selectAll().where { Book_Copies.checkedOutById.isNotNull() }
            .map(::mapRowToLoan)
    }

    fun getOverdueBooks(): List<LoanWithDetails> = transaction {
        val today = LocalDate.now().toString() // "YYYY-MM-DD"

        loansJoin
            .selectAll().where { Book_Copies.dueDate less today }
            .map(::mapRowToLoan)
    }

    fun getCustomerLoans(criteria: CustomerSearch): List<LoanWithDetails> = transaction {

        // This is NOT a boolean, its a AQL where exprssion
        // we need to  ste it to TRUE to add more stuff to
        var condition: Op<Boolean> = Op.TRUE

        // If criteria.id is not null, add "AND customerId = <id>"
        criteria.id?.let { id ->
            condition = condition and (Customers.customerId eq id)
        }

        // If criteria.firstName is not null, add "AND firstName = <first>"
        criteria.firstName?.let { first ->
            condition = condition and (Customers.firstName eq first)
        }

        // If criteria.lastName is not null, add "AND lastName = <last>"
        criteria.lastName?.let { last ->
            condition = condition and (Customers.lastName eq last)
        }

//        var condition: Op<Boolean> = Op.TRUE
//
//        if (criteria.id != null) {
//            condition = condition and (Customers.customerId eq criteria.id)
//        }
//
//        if (criteria.firstName != null) {
//            condition = condition and (Customers.firstName eq criteria.firstName)
//        }
//
//        if (criteria.lastName != null) {
//            condition = condition and (Customers.lastName eq criteria.lastName)
//        }

        loansJoin
            .select { condition }
            .map(::mapRowToLoan)
    }







}