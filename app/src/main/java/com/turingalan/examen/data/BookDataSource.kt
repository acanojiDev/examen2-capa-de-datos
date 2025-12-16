package com.turingalan.examen.data

import com.turingalan.examen.data.model.Book
import kotlinx.coroutines.flow.Flow


// TODO Completar con los métodos necesarios
interface BookDataSource {
    fun observe(): Flow<Result<List<Book>>>

    suspend fun readOne(id:String): Result<Book>

    suspend fun readAll(): Result<List<Book>>
    suspend fun readByName(title:String):Result<List<Book>>
}