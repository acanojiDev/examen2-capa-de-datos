package com.turingalan.examen.data.repository

import com.turingalan.examen.data.model.Book
import kotlinx.coroutines.flow.Flow

/**
 * No se puede modificar esta interfaz
 */
interface BookRepository {

    suspend fun readOne(id:String):Result<Book>
    suspend fun observeByQuery(search:String):Flow<Result<List<Book>>>
}