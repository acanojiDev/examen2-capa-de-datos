package com.turingalan.examen.di

import android.content.Context
import androidx.room.Room
import com.turingalan.examen.data.local.BookDao
import com.turingalan.examen.data.local.BookDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule{
    @Provides
    @Singleton
    fun provideBookDatabase(@ApplicationContext context: Context): BookDatabase{
        return Room.databaseBuilder(
            context,
            BookDatabase::class.java,
            "book_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideBookDao(database: BookDatabase): BookDao {
        return database.bookDao()
    }
}