package com.icchi.android.tiny_room

data class Item(
    var id: Int,
    var folderId: Int,
    var image: String,
    var purchaseDate: String,
    var price: Double,
    var note: String
)

data class Folder(
    var id: Int,
    var folderName: String
)
