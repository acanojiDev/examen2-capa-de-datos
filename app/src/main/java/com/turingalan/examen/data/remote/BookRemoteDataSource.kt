package com.turingalan.examen.data.remote

import com.turingalan.examen.common.exception.BookNotFoundException
import com.turingalan.examen.data.BookDataSource
import com.turingalan.examen.data.model.Book
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class BookRemoteDataSource @Inject constructor(
    private val bookApi: BookApi,
    private val scope: CoroutineScope
): BookDataSource{

    suspend fun searchBooksByTitle(search: String): Response<BookListRemote> {
        val query = "title:$search"
        return bookApi.searchByTitle(query)
    }

    fun extractWorkIds(books: BookListRemote?): List<String> {
        if (books == null) return emptyList()
        return books.items.map { book -> getWorkId(book.id) }
    }

    override fun observe(): Flow<Result<List<Book>>> {
        TODO("Not yet implemented")
    }

    override suspend fun readOne(id: String): Result<Book> {
        TODO("Not yet implemented")
    }


    override suspend fun readAll(): Result<List<Book>> {

        return Result.success(emptyList())
    }

    override suspend fun readByName(title: String): Result<List<Book>> {
        return try {
            val response = searchBooksByTitle(title)
            if (response.isSuccessful) {
                val bookList = response.body()?.items ?: emptyList()
                val books = bookList.map {
                    Book(it.id, it.title, it.authors, it.publicationYear)
                }
                Result.success(books)
            } else {
                Result.failure(BookNotFoundException())
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}