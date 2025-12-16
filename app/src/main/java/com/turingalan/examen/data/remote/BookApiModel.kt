package com.turingalan.examen.data.remote

data class BookListRemote(
    val items: List<BookListItemRemote>
)

data class BookListItemRemote(
    val id:String,
    val title:String,
    val authors:List<String>,
    val publicationYear:Int
)

data class BookRemote(
    val id:String,
    val title:String,
    val authors: List<String>,
    val publicationYear: Int
)