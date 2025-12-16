package com.turingalan.examen.data.repository

import com.turingalan.examen.common.exception.BookNotFoundException
import com.turingalan.examen.data.local.BookLocalDataSource
import com.turingalan.examen.data.model.Book
import com.turingalan.examen.data.remote.BookRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val localDataSource: BookLocalDataSource,
    private val remoteDataSource: BookRemoteDataSource
): BookRepository{

    private suspend fun searchBooks(title: String): Result<List<Book>> {
        return try {
            val response = remoteDataSource.searchBooksByTitle(title)
            if (response.isSuccessful) {
                val bookListRemote = response.body()
                if (bookListRemote != null) {
                    val books = bookListRemote.items.map {
                        Book(it.id, it.title, it.authors, it.publicationYear)
                    }
                    Result.success(books)
                } else {
                    Result.failure(BookNotFoundException())
                }
            } else {
                Result.failure(BookNotFoundException())
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun searchBooksWithWorkIds(title: String): Flow<Result<List<Book>>> = flow {
        try {
            val response = remoteDataSource.searchBooksByTitle(title)
            if (response.isSuccessful) {
                val bookListRemote = response.body()
                if (bookListRemote != null) {
                    val books = bookListRemote.items.map {
                        Book(it.id, it.title, it.authors, it.publicationYear)
                    }
                    emit(Result.success(books))
                } else {
                    emit(Result.failure(BookNotFoundException()))
                }
            } else {
                emit(Result.failure(BookNotFoundException()))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override suspend fun readOne(id: String): Result<Book> {
        return localDataSource.readOne(id)
    }

    override suspend fun observeByQuery(search: String): Flow<Result<List<Book>>> {
        return searchBooksWithWorkIds(search)
    }

}