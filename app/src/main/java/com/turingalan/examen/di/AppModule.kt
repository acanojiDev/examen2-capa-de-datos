package com.turingalan.examen.di


import com.turingalan.examen.data.BookDataSource
import com.turingalan.examen.data.local.BookLocalDataSource
import com.turingalan.examen.data.remote.BookRemoteDataSource
import com.turingalan.examen.data.repository.BookRepository
import com.turingalan.examen.data.repository.BookRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Singleton
    @Binds
    @RemoteDataSource
    abstract fun bindsRemoteBookRemoteDataSource(ds: BookRemoteDataSource): BookDataSource

    @Singleton
    @Binds
    @LocalDataSource
    abstract fun bindsLocalBookRemoteDataSource(ds: BookLocalDataSource): BookDataSource


    @Binds
    @Singleton
    abstract fun bindRepository(repository: BookRepositoryImpl): BookRepository

}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LocalDataSource

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RemoteDataSource