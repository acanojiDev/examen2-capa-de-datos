package com.turingalan.examen.data.remote

import com.google.android.libraries.places.api.net.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface BookApi {
    /**
     * Método para buscar libros por título
     * Get al /search.json
     * Por ejemplo para buscar "The Two towers" tenemos que hacer
     * GET /search.json?q=title:The Two Towers
     *
     * Para forzar la respuesta en JSON pasarle la cabecera Accept: application/json
     * con la anotación Headers
     */


    @Headers("Accept: application/json")
    @GET("search.json")
    suspend fun searchByTitle(
        @Query("q") query: String
    ): Response<BookListRemote>


}



