package br.com.syd.sharedpreferencessample

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.syd.sharedpreferencessample.Contants.CHAVE_TAREFAS
import br.com.syd.sharedpreferencessample.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class MainActivity : AppCompatActivity() {

    private lateinit var databind: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(databind.root)

        val lTarefas = getTarefas()

        if (lTarefas.size > 0) {
            lTarefas.sortBy { it.prioridade }
            var tarefaText = ""
            for (tarefa: TarefaModel in lTarefas) {
                tarefaText += " " + tarefa.prioridade + " - " + tarefa.conteudo + "\n"
            }
            databind.Tarefas.text = tarefaText
        } else {
            databind.Tarefas.text = "nada para fazer"
        }

        databind.addTarefabutton.setOnClickListener {

            if (databind.editTextPrioridade.text.toString() != "") {
                val txt = databind.editTextPrioridade.text
                val prioridade: Int = Integer.parseInt(databind.editTextPrioridade.text.toString())

                val conteudo = databind.editTextConteudo.text.toString()

                lTarefas.add(TarefaModel(prioridade, conteudo))

                SaveList(lTarefas)
            }
        }

    }

    fun getTarefas(): ArrayList<TarefaModel> {

        val sharedPref = this.getSharedPreferences(CHAVE_TAREFAS, Context.MODE_PRIVATE)
        val gsonValue = sharedPref?.getString(CHAVE_TAREFAS, null)
        if (gsonValue != null) {
            val itemType: Type = object : TypeToken<ArrayList<TarefaModel>>() {}.type
            return Gson().fromJson(gsonValue, itemType)
        }
        return ArrayList()
    }

    private fun SaveList(favorites: ArrayList<TarefaModel>) {
        /*val sharedPref =
            this.getSharedPreferences(CHAVE_TAREFAS, Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            val json = Gson().toJson(favorites)
            putString(CHAVE_TAREFAS, json)
            apply()
        }*/
        val sharedPreference =  getSharedPreferences(CHAVE_TAREFAS,Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        val json = Gson().toJson(favorites)
        editor.putString(CHAVE_TAREFAS,json)
        editor.apply()
    }
}