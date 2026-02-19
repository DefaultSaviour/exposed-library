package org.example.data.tables
import org.jetbrains.exposed.sql.Table

object Books : Table("Books") {
    val isbn = text("isbn")
    val title = text("Title")
    val author = text("Author")
    val genre = text("Genre")
    val pageCount = integer("PageCount")
    val summary = text("Summary")

    override val primaryKey = PrimaryKey(isbn)
}
