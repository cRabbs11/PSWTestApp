package com.example.pswtestapp.di.modules

import android.content.Context
import androidx.room.Room
import com.example.pswtestapp.data.AppDatabase
import com.example.pswtestapp.data.dao.ProductDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule(val context: Context) {

    @Provides
    fun provideContext(): Context = context

    @Singleton
    @Provides
    fun provideArtistDao(): ProductDao {
        val database = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.PRODUCTS_TABLE_NAME
        ).build().productDao()
        return database
    }
}