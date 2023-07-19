package com.icchi.android.tiny_room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var itemViewModel: ItemViewModel
    private lateinit var folderViewModel: FolderViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)
        folderViewModel = ViewModelProvider(this).get(FolderViewModel::class.java)

        // Observe the data from ViewModel
        itemViewModel.readItems().observe(this, Observer { items ->
            // Handle items
        })

        folderViewModel.readFolders().observe(this, Observer { folders ->
            // Handle folders
        })
    }

    // Insert a new item
    private fun insertItem() {
        val newItem = Item(0, 1, "image.png", "2023-07-19", 1000.0, "Note")
        itemViewModel.createItem(newItem).observe(this, Observer { success ->
            if (success) {
                // Handle success
            } else {
                // Handle error
            }
        })
    }

    // Insert a new folder
    private fun insertFolder() {
        val newFolder = Folder(0, "New Folder")
        folderViewModel.createFolder(newFolder).observe(this, Observer { success ->
            if (success) {
                // Handle success
            } else {
                // Handle error
            }
        })
    }
}
