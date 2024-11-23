package com.example.lucasdiasmorosini_rm94523.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lucasdiasmorosini_rm94523.model.ItemModel

@Database(entities = [ItemModel::class], version = 1)
abstract class ItemDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao
}