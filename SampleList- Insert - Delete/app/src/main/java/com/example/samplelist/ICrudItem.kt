package com.example.samplelist

import com.example.samplelist.model.ItemObject

interface ICrudItem {
    fun AddItem(item: ItemObject)
    fun ExcludeItem(item: ItemObject)
    fun EditItem(item: ItemObject, index: Int)
}