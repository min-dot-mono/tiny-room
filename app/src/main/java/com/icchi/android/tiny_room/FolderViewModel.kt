package com.icchi.android.tiny_room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FolderViewModel(application: Application) : AndroidViewModel(application) {
    private val folderDao: FolderDao = FolderDao(application)

    fun createFolder(folder: Folder): LiveData<Boolean> {
        val result = MutableLiveData<Boolean>()
        viewModelScope.launch(Dispatchers.IO) {
            result.postValue(folderDao.createFolder(folder))
        }
        return result
    }

    fun readFolders(): LiveData<List<Folder>> {
        val result = MutableLiveData<List<Folder>>()
        viewModelScope.launch(Dispatchers.IO) {
            result.postValue(folderDao.readFolders())
        }
        return result
    }

    fun updateFolder(folder: Folder): LiveData<Int> {
        val result = MutableLiveData<Int>()
        viewModelScope.launch(Dispatchers.IO) {
            result.postValue(folderDao.updateFolder(folder))
        }
        return result
    }

    fun deleteFolder(id: Int): LiveData<Int> {
        val result = MutableLiveData<Int>()
        viewModelScope.launch(Dispatchers.IO) {
            result.postValue(folderDao.deleteFolder(id))
        }
        return result
    }
}
