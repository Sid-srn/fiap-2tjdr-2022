package com.example.samplelist

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.samplelist.Constants.INDEX_EXTRA
import com.example.samplelist.Constants.INDEX_EXTRA_RESULT
import com.example.samplelist.Constants.ITEM_EXTRA
import com.example.samplelist.Constants.ITEM_EXTRA_RESULT
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

        dataBind.addItem.setOnClickListener() {
            /*listeItemAdapter.AddListItem(
                ItemObject(
                    "valor auto$count",
                    "Descrição conteudo gerado auto$count"
                )
            )
            count++*/
            AddItem()
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

    override fun AddItem() {
        val intent = Intent(this, EditItemActivity::class.java)
        register.launch(intent)
    }

    override fun ExcludeItem(item: ItemObject) {
        val confirmDialog = AlertDialog.Builder(this)
        confirmDialog.setTitle("Exclusão")
        confirmDialog.setMessage("Confirma a exclusão do item "+ item.textoItem + "?")
        confirmDialog.setPositiveButton("Sim"){_, _->
            listeItemAdapter.removeListItem(item)
        }
        confirmDialog.setNegativeButton("Não"){dialog,_->
            dialog.cancel()
        }
        confirmDialog.show()
    }

    override fun EditItem(item: ItemObject, index: Int) {
        val intent = Intent(this, EditItemActivity::class.java)
        intent.putExtra(ITEM_EXTRA, item)
        intent.putExtra(INDEX_EXTRA, index)
        register.launch(intent)
    }

    private val register = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if(result.resultCode == Activity.RESULT_OK){
            result.data?.let{data ->
                if(data.hasExtra(ITEM_EXTRA_RESULT)){
                    val itemResult = data.getParcelableExtra<ItemObject>(ITEM_EXTRA_RESULT)
                    if(itemResult != null){
                        val index = data.getIntExtra(INDEX_EXTRA_RESULT, -1)
                        if(index>=0){
                            listeItemAdapter.editListItem(itemResult, index)
                        }else{
                            listeItemAdapter.AddListItem(itemResult)
                        }
                    }
                }

            }
        }
    }
}