package org.example.domain.models

data class BookCopyWithBook(
    val copyId: Int,
    val isbn: String,
    val title: String,
    val author: String,
    val genre: String,
    val pageCount: Int,
    val summary: String,
    val checkedOutById: Int?,
    val dueDate: String?
)

// we are joining book and copy so idiomatically it make sense to create
// a new data class with all the shared properties, rather than creating
// 2 potential partially filled objects or objets with a bunch of nulls
// this is for:
// getAllCopiesWithBookInfo()
// getAvailableCopies(isbn)
// getCopyDetails(copyId)