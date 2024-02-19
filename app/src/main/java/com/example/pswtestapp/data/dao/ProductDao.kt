package com.example.pswtestapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pswtestapp.data.AppDatabase
import com.example.pswtestapp.data.entity.Product

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products: List<Product>)

    @Query("SELECT * FROM ${AppDatabase.PRODUCTS_TABLE_NAME}")
    suspend fun getProducts() : List<Product>

    @Query("SELECT * FROM ${AppDatabase.PRODUCTS_TABLE_NAME} WHERE category LIKE :category")
    suspend fun getProducts(category: String) : List<Product>

    @Query("SELECT DISTINCT category FROM ${AppDatabase.PRODUCTS_TABLE_NAME}")
    suspend fun getCategory() : List<String>

    @Query("SELECT * FROM ${AppDatabase.PRODUCTS_TABLE_NAME} WHERE id LIKE :id")
    suspend fun getProduct(id: Int) : Product?
}