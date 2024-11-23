package com.example.lucasdiasmorosini_rm94523.viewmodel

import android.app.Application
import android.content.ClipData.Item
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.lucasdiasmorosini_rm94523.data.ItemDao
import com.example.lucasdiasmorosini_rm94523.data.ItemDatabase
import com.example.lucasdiasmorosini_rm94523.model.ItemModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemsViewModel(application: Application) : AndroidViewModel(application) {

    private val itemDao: ItemDao

    val itemsLiveData: LiveData<List<ItemModel>>

    init {
        val database = Room.databaseBuilder(
            getApplication(),
            ItemDatabase::class.java,
            "items_database"
        ).build()

        itemDao = database.itemDao()

        itemsLiveData = itemDao.getAll()
    }

    //fun addItem(name: String, desc: String) {
    fun addItem(item: String) {
        viewModelScope.launch(Dispatchers.IO) {
            //val newItem = ItemModel(titulo = name, descricao = desc)
            val newItem = ItemModel(name = item)
            itemDao.insert(newItem)
        }
    }


    fun removeItem(item: ItemModel) {
        viewModelScope.launch(Dispatchers.IO) {
            itemDao.delete(item)
        }
    }
}