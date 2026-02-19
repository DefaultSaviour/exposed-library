package org.example.data.tables

import org.jetbrains.exposed.sql.Table

object Book_Copies : Table("Book_Copies") {
    val copyId = integer("CopyId").autoIncrement()
    val isbn = reference("isbn", Books.isbn)
    val checkedOutById = reference("CheckedOutById", Customers.customerId).nullable()
    val dueDate = text("DueDate").nullable()

    override val primaryKey = PrimaryKey(copyId)
}

