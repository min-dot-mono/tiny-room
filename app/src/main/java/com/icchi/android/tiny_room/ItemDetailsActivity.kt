package com.icchi.android.tiny_room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class ItemDetailsActivity : AppCompatActivity() {

    private lateinit var itemViewModel: ItemViewModel
    private var itemId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_details)

        itemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)

        itemId = intent.getIntExtra("itemId", 0) // or any default value

        // Observe the item data from ViewModel
        itemViewModel.readItem(itemId).observe(this, Observer { item ->
            // Display item details
        })
    }

    // Update item details
    private fun updateItem() {
        val updatedItem = Item(itemId, 1, "image.png", "2023-07-19", 1000.0, "Updated Note")
        itemViewModel.updateItem(updatedItem).observe(this, Observer { affectedRows ->
            if (affectedRows > 0) {
                // Handle success
            } else {
                // Handle error
            }
        })
    }

    // Delete item
    private fun deleteItem() {
        itemViewModel.deleteItem(itemId).observe(this, Observer { affectedRows ->
            if (affectedRows > 0) {
                // Handle success
            } else {
                // Handle error
            }
        })
    }
}
