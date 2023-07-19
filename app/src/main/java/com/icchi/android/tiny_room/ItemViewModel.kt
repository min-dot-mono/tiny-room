package com.icchi.android.tiny_room

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemViewModel(application: Application) : AndroidViewModel(application) {
    private val itemDao: ItemDao = ItemDao(application)

    fun createItem(item: Item): LiveData<Boolean> {
        val result = MutableLiveData<Boolean>()
        viewModelScope.launch(Dispatchers.IO) {
            result.postValue(itemDao.createItem(item))
        }
        return result
    }

    fun readItems(): LiveData<List<Item>> {
        val result = MutableLiveData<List<Item>>()
        viewModelScope.launch(Dispatchers.IO) {
            result.postValue(itemDao.readItems())
        }
        return result
    }

    fun updateItem(item: Item): LiveData<Int> {
        val result = MutableLiveData<Int>()
        viewModelScope.launch(Dispatchers.IO) {
            result.postValue(itemDao.updateItem(item))
        }
        return result
    }

    fun deleteItem(id: Int): LiveData<Int> {
        val result = MutableLiveData<Int>()
        viewModelScope.launch(Dispatchers.IO) {
            result.postValue(itemDao.deleteItem(id))
        }
        return result
    }
}
