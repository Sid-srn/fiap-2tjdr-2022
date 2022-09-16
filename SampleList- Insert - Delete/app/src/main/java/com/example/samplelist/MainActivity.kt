package com.example.samplelist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.samplelist.Constants.INDEX_EXTRA
import com.example.samplelist.Constants.ITEM_EXTRA
import com.example.samplelist.databinding.ActivityMainBinding
import com.example.samplelist.model.ItemObject

class MainActivity : AppCompatActivity(), ICrudItem {
    lateinit var dataBind: ActivityMainBinding
    val listeItemAdapter = ListeItemAdapter(this)
    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(dataBind.root)

        
        criaRecycler()

        dataBind.addItem.setOnClickListener(){
            listeItemAdapter.AddListItem(ItemObject("valor auto$count", "Descrição conteudo gerado auto$count"))
            count++
        }

    }

    private fun criaRecycler() {
        dataBind.lista.layoutManager = LinearLayoutManager(this)

        val conteudo = mutableListOf<ItemObject>()

        conteudo.add(ItemObject("valor 1", "descrição do conteudo 1 "))
        conteudo.add(ItemObject("valor 2", "descrição do conteudo 2 "))
        conteudo.add(ItemObject("valor 3", "descrição do conteudo 3 "))
        conteudo.add(ItemObject("valor 4", "descrição do conteudo 4 "))

        listeItemAdapter.setItensList(conteudo)

        dataBind.lista.adapter = listeItemAdapter

    }

    override fun AddItem(item: ItemObject) {
        TODO("Not yet implemented")
    }

    override fun ExcludeItem(item: ItemObject) {
        TODO("Not yet implemented")
    }

    override fun EditItem(item: ItemObject, index: Int) {
        val intent = Intent(this, EditItemActivity::class.java)
        intent.putExtra(ITEM_EXTRA, item)
        intent.putExtra(INDEX_EXTRA, index)
        startActivity(intent)
    }
}