package org.example.data.repository

import org.example.data.tables.Customers
import org.example.domain.models.Customer
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class CustomerRepo {

    fun getAllCustomers(): List<Customer> = transaction {
        Customers.selectAll().map {row ->
            Customer(
                customerId =  row[Customers.customerId],
                firstName =  row[Customers.firstName],
                lastName = row[Customers.lastName],
                contactNo = row[Customers.contactNo],
        )
        }
    }

    fun addCustomer(customer: Customer):String = transaction {
        try {
            val id = Customers.insert {
                it[firstName] = customer.firstName
                it[lastName] = customer.lastName
                it[contactNo] = customer.contactNo

            } get Customers.customerId
            "Customer ${customer.firstName} created with id $id"
        } catch (e: Exception){
            "Customer ${customer.firstName} could not be added: ${e.message}"
    }
    }
}