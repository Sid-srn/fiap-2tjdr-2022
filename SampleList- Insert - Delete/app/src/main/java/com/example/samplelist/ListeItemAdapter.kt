package com.example.samplelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.samplelist.databinding.ItemCellBinding
import com.example.samplelist.model.ItemObject

class ListeItemAdapter : RecyclerView.Adapter<ListeItemAdapter.ItemHolder>() {

    private val items: MutableList<ItemObject> = mutableListOf()

    class ItemHolder(val itemCell: ItemCellBinding) : RecyclerView.ViewHolder(itemCell.root) {
        fun bind(itemObject: ItemObject) {
            itemCell.textViewTextoItem.text = itemObject.textoItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            ItemCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(items[position])
        holder.itemCell.removeItemBtn.setOnClickListener(){
            removeListItem(items[position])
        }
    }

    override fun getItemCount(): Int =
        items.size

    fun setItensList(listItems: List<ItemObject>){
        items.clear()
        items.addAll(listItems)
        notifyDataSetChanged()
    }

    fun AddListItem(item:ItemObject){
        items.add(item)
        notifyItemInserted(items.size)
    }

    fun removeListItem(item:ItemObject){
        val removeIndex = items.indexOf(item)
        items.remove(item)
        notifyItemRemoved(removeIndex)
        notifyItemRangeChanged(removeIndex, items.size)
    }
}