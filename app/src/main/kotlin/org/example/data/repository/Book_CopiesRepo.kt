package org.example.data.repository



import org.example.data.tables.Book_Copies

import org.example.data.tables.Books
import org.example.domain.models.BookCopy
import org.example.domain.models.BookCopyWithBook
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.isNull
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction


import kotlin.String

class Book_CopiesRepo
{

    fun getAllBook_Copies(): List<BookCopy> = transaction {
        Book_Copies.selectAll().map { row ->
            BookCopy(
                copyId = row[Book_Copies.copyId],
                isbn = row[Book_Copies.isbn],
                checkedOutById = row[Book_Copies.checkedOutById],
                dueDate = row[Book_Copies.dueDate]
            )

        }
    }

    // ever instance of each book
    fun getAllCopiesWithBookInfo(): List<BookCopyWithBook> = transaction {
        (Book_Copies innerJoin Books)
            .selectAll()
            .map (::mapRowToBookCopy)
    }


    // get only available copies of each book, search by name or ISBN
    fun getAvailableCopies(search: String): List<BookCopyWithBook> = transaction {
        val condition =
            ((Books.isbn.lowerCase() eq search.lowercase()) or
                    (Books.title.lowerCase() eq search.lowercase())) and
                    Book_Copies.checkedOutById.isNull()
        (Book_Copies innerJoin Books)
            .selectAll().where { condition }
            .map (::mapRowToBookCopy
                )
            }



    fun getCopyDetails(copyId: Int): BookCopyWithBook? = transaction {
        (Book_Copies innerJoin Books)
            .selectAll().where { Book_Copies.copyId eq copyId }
            .map  (::mapRowToBookCopy)
            }.singleOrNull()


    private fun mapRowToBookCopy(row: ResultRow) = BookCopyWithBook(
        copyId = row[Book_Copies.copyId],
        isbn = row[Books.isbn],
        title = row[Books.title],
        author = row[Books.author],
        genre = row[Books.genre],
        pageCount = row[Books.pageCount],
        summary = row[Books.summary],
        checkedOutById = row[Book_Copies.checkedOutById],
        dueDate = row[Book_Copies.dueDate]
    )

}




