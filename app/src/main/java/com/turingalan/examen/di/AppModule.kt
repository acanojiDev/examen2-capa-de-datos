package com.turingalan.examen.di


import com.turingalan.examen.data.BookDataSource
import com.turingalan.examen.data.remote.BookRemoteDataSource
import com.turingalan.examen.data.repository.BookRepository
import com.turingalan.examen.data.repository.BookRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {


    @Binds
    @Singleton
    abstract fun bindRepository(repository: BookRepositoryImpl): BookRepository

}