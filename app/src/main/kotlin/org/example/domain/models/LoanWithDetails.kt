package org.example.domain.models

import java.time.LocalDate

data class LoanWithDetails(
    val copyId: Int,
    val isbn: String,
    val title: String,
    val customerId: Int,
    val firstName: String,
    val lastName: String,
    val dueDate: String?
)

