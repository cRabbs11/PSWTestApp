package com.example.pswtestapp.domain

import android.util.Log
import com.example.pswtestapp.data.dao.ProductDao
import com.example.pswtestapp.data.entity.Product
import com.example.pswtestapp.utils.RoomData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class Interactor(val productDao: ProductDao) {

    fun fillRoom() {
        MainScope().launch(Dispatchers.IO) {
            Log.d("BMTH", "check room...")
            if (productDao.getProducts().isEmpty()) {
                val roomData = RoomData()
                productDao.insertProducts(roomData.getProductList())
            }
            Log.d("BMTH", "check room ended")
        }
    }

    suspend fun getCategories(): List<String> = productDao.getCategory()

    suspend fun getProducts(): List<Product> = productDao.getProducts()

    suspend fun getProducts(category: String): List<Product> = productDao.getProducts(category)

    suspend fun getProduct(id: Int) = productDao.getProduct(id)
}