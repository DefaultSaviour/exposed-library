package org.example.domain.models

data class Customer (
    val customerId : Int? =  null,
    val firstName: String,
    val lastName: String,
    val contactNo: Int
    )