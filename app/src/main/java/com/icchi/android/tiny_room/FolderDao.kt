package com.icchi.android.tiny_room

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class FolderDao(context: Context) {
    private val dbHelper: DatabaseHelper = DatabaseHelper(context)
    private val db: SQLiteDatabase = dbHelper.writableDatabase

    fun createFolder(folder: Folder): Boolean {
        val contentValues = ContentValues().apply {
            put("folderName", folder.folderName)
        }

        val result = db.insert("folders", null, contentValues)
        return result != -1L
    }

    fun readFolders(): List<Folder> {
        val folders = mutableListOf<Folder>()
        val cursor: Cursor = db.rawQuery("SELECT * FROM folders", null)

        if (cursor.moveToFirst()) {
            do {
                val folder = Folder(
                    cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("folderName"))
                )
                folders.add(folder)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return folders
    }

    fun updateFolder(folder: Folder): Int {
        val contentValues = ContentValues().apply {
            put("folderName", folder.folderName)
        }

        return db.update("folders", contentValues, "id = ?", arrayOf(folder.id.toString()))
    }

    fun deleteFolder(id: Int): Int {
        return db.delete("folders", "id = ?", arrayOf(id.toString()))
    }
}
