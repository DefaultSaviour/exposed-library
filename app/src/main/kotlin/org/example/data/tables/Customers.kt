package org.example.data.tables
import org.jetbrains.exposed.sql.Table

object Customers : Table(name = "Customers") {
    val customerId = integer("CustomerId").autoIncrement()
    val firstName = text("FirstName")
    val lastName = text("LastName")
    val contactNo = integer("ContactNo")

    override val primaryKey = PrimaryKey(customerId)
}
