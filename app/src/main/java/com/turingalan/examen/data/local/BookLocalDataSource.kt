package com.turingalan.examen.data.local

import com.turingalan.examen.common.exception.BookNotFoundException
import com.turingalan.examen.data.BookDataSource
import com.turingalan.examen.data.model.Book
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookLocalDataSource @Inject constructor(
    private val bookDao: BookDao
): BookDataSource{
    override fun observe(): Flow<Result<List<Book>>> {
        return bookDao.observeAll()
            .map { entities ->
                try {
                    Result.success(entities.toModel())
                }catch (e: Exception){
                    Result.failure(e)
                }
            }
    }

    override suspend fun readAll(): Result<List<Book>> {
        return  try{
            val entities = bookDao.getAll()
            Result.success(entities.toModel())
        }catch (e: Exception){
            Result.failure(e)
        }
    }

    override suspend fun readOne(id: String): Result<Book> {
        return try {
            val entity = bookDao.readOne(id)
            if(entity!=null){
                Result.success(entity.toModel())
            }else{
                Result.failure(BookNotFoundException())
            }
        }catch (e: Exception){
            Result.failure(e)
        }
    }

    override suspend fun readByName(title: String): Result<List<Book>> {
        return try {
            val entities = bookDao.getAllByName(title)
            Result.success(entities.toModel())
        }catch (e: Exception){
            Result.failure(e)
        }
    }


}