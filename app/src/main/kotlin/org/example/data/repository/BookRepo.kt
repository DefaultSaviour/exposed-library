package org.example.data.repository

import org.example.domain.models.Book
import org.example.data.tables.Books

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction


class BookRepo
{

    fun getAllBooks():List<Book> = transaction {
        Books.selectAll().map { row ->
            Book(
                isbn = row[Books.isbn],
                title = row[Books.title],
                author = row[Books.author],
                genre = row[Books.genre],
                pageCount = row[Books.pageCount],
                summary = row[Books.summary]
            )
        }
    }

    fun isbnExists(isbn:String):Boolean = transaction {
        Books.select { Books.isbn.eq(isbn) }.count() > 0
    }

    fun addBook(book:Book):String = transaction {
        //run isbnExists first so the user doesn't need enter all details
        //before they find out if the books exists or not
        try {
            Books.insert {
                it[isbn] = book.isbn
                it[title] = book.title
                it[author] = book.author
                it[genre] = book.genre
                it[pageCount] = book.pageCount
                it[summary] = book.summary
            }

            "Book '${book.title}' added successfully"

        } catch (e: Exception) {
            "Failed to add book '${book.title}': ${e.message}"
        }
    }

    fun updateBook (book:Book):String = transaction {
        //run isbnExists first so the user doesn't need enter all details
        //before they find out if the books exists or not
        try {
            Books.update({ Books.isbn.eq(book.isbn) }) {
                it[title] = book.title
                it[author] = book.author
                it[genre] = book.genre
                it[pageCount] = book.pageCount
                it[summary] = book.summary
            }
            "Book '${book.title}' updated successfully"

        } catch (e: Exception){
            "Failed to update book '${book.title}': ${e.message}"
        }
    }

    fun deleteBook(book:Book):String = transaction {
        //run isbnExists first so the user doesn't need enter all details
        //before they find out if the books exists or not
        try {
            Books.deleteWhere { Books.isbn.eq(book.isbn) }

            "Book '${book.title}' removed successfully"

        }catch (e: Exception){"Failed to delete book '${book.title}': ${e.message}"}


    }



}