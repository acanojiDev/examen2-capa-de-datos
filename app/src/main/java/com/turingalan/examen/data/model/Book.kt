package com.turingalan.examen.data.model

data class Book(
    val id: String,
    val title: String = "",
    val authors: List<String> = listOf(),
    val publicationYear: Int = 9999,
)