package org.example.domain.models

data class Book(
    val isbn: String // unique code for each  book
    ,val title:String
    ,val author:String
    ,val genre:String
    ,val pageCount:Int
    ,val summary:String
)




