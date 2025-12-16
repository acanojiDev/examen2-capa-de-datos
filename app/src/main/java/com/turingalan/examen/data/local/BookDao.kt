package com.turingalan.examen.data.local

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao{
    @Query("SELECT * FROM book")
    suspend fun getAll(): List<BookEntity>

    @Query("SELECT * FROM book")
    fun observeAll(): Flow<List<BookEntity>>

    @Query("SELECT * FROM book WHERE id = :id")
    suspend fun readOne(id:String): BookEntity

    @Query("SELECT * FROM book WHERE title = :title")
    suspend fun getAllByName(title: String): List<BookEntity>

}