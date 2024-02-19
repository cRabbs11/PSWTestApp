package com.example.pswtestapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pswtestapp.data.dao.ProductDao
import com.example.pswtestapp.data.entity.Product

@Database(entities = [
    Product::class],
    version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun productDao(): ProductDao

    companion object {
        const val PRODUCTS_TABLE_NAME = "products"
    }
}

