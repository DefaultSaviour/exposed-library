package org.example.data.database

import org.example.data.tables.Books
import org.example.data.tables.Book_Copies
import org.example.data.tables.Customers
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.Connection

object DatabaseFactory {

    fun init() {
        Database.connect(
            url = "jdbc:sqlite:database/library.db",
            driver = "org.sqlite.JDBC"
        )

        transaction {
        }
    }
}



