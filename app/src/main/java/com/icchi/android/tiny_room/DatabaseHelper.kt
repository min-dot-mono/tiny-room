package com.icchi.android.tiny_room

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "tinyroom.db"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createItemTableQuery = """
            CREATE TABLE items (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                folderId INTEGER,
                image TEXT,
                purchaseDate TEXT,
                price REAL,
                note TEXT
            )
        """.trimIndent()

        val createFolderTableQuery = """
            CREATE TABLE folders (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                folderName TEXT
            )
        """.trimIndent()

        db.execSQL(createItemTableQuery)
        db.execSQL(createFolderTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // For now, just drop older tables and create new ones
        db.execSQL("DROP TABLE IF EXISTS items")
        db.execSQL("DROP TABLE IF EXISTS folders")
        onCreate(db)
    }
}
