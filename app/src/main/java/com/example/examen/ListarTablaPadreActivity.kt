package com.example.examen

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.AbsListView
import android.widget.ListView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_listar_tabla_padre.*
import org.jetbrains.anko.find

class ListarTablaPadreActivity : AppCompatActivity() {


    lateinit var listView:ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_tabla_padre)


        listView=findViewById(R.id.listviewpadre)
        val adapter=EquipoAdapter(applicationContext,R.layout.equipos,listaequipos)
        listView.adapter=adapter


    }
}
