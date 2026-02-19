package org.example.domain.models


data class BookCopy(
    val copyId: Int,
    val isbn: String,
    val checkedOutById: Int?,
    val dueDate: String?
)
