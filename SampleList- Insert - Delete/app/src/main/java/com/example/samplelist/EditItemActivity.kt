package com.example.samplelist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.samplelist.Constants.INDEX_EXTRA
import com.example.samplelist.Constants.INDEX_EXTRA_RESULT
import com.example.samplelist.Constants.ITEM_EXTRA
import com.example.samplelist.Constants.ITEM_EXTRA_RESULT
import com.example.samplelist.databinding.ActivityEditItemBinding
import com.example.samplelist.model.ItemObject

class EditItemActivity : AppCompatActivity() {
    lateinit var dataBind: ActivityEditItemBinding

    var detalheItem: ItemObject? = null
    var indexItem: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBind = ActivityEditItemBinding.inflate(layoutInflater)
        setContentView(dataBind.root)

        detalheItem = intent.getParcelableExtra(ITEM_EXTRA)
        indexItem = intent.getIntExtra(INDEX_EXTRA, -1)

        if (detalheItem != null){
            dataBind.txtItem.setText(detalheItem?.textoItem)
            dataBind.txtItemDetail.setText(detalheItem?.detailItem)
        }

        dataBind.btnConfirmar.setOnClickListener {
            Intent().apply {
                putExtra(ITEM_EXTRA_RESULT, ItemObject(
                    dataBind.txtItem.text.toString(),
                    dataBind.txtItemDetail.text.toString()
                ))
                putExtra(INDEX_EXTRA_RESULT, indexItem)
                setResult(RESULT_OK, this)
            }
            this.finish()
        }

    }
}