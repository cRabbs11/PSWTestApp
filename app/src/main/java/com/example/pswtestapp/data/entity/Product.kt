package com.example.pswtestapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.pswtestapp.data.AppDatabase
import java.io.Serializable

@Entity(tableName = AppDatabase.PRODUCTS_TABLE_NAME, indices = [Index(value = ["id"], unique = true)])
data class Product(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "category") var category: String,
    @ColumnInfo(name = "image") var image: String
)