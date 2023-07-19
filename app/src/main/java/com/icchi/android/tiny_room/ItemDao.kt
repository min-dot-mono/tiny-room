package com.icchi.android.tiny_room

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class ItemDao(context: Context) {
    private val dbHelper: DatabaseHelper = DatabaseHelper(context)
    private val db: SQLiteDatabase = dbHelper.writableDatabase

    fun createItem(item: Item): Boolean {
        val contentValues = ContentValues().apply {
            put("folderId", item.folderId)
            put("image", item.image)
            put("purchaseDate", item.purchaseDate)
            put("price", item.price)
            put("note", item.note)
        }

        val result = db.insert("items", null, contentValues)
        return result != -1L
    }

    fun readItems(): List<Item> {
        val items = mutableListOf<Item>()
        val cursor: Cursor = db.rawQuery("SELECT * FROM items", null)

        if (cursor.moveToFirst()) {
            do {
                val item = Item(
                    cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getInt(cursor.getColumnIndex("folderId")),
                    cursor.getString(cursor.getColumnIndex("image")),
                    cursor.getString(cursor.getColumnIndex("purchaseDate")),
                    cursor.getDouble(cursor.getColumnIndex("price")),
                    cursor.getString(cursor.getColumnIndex("note"))
                )
                items.add(item)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return items
    }

    fun updateItem(item: Item): Int {
        val contentValues = ContentValues().apply {
            put("folderId", item.folderId)
            put("image", item.image)
            put("purchaseDate", item.purchaseDate)
            put("price", item.price)
            put("note", item.note)
        }

        return db.update("items", contentValues, "id = ?", arrayOf(item.id.toString()))
    }

    fun deleteItem(id: Int): Int {
        return db.delete("items", "id = ?", arrayOf(id.toString()))
    }
}
