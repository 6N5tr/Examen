package com.example.examen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.FirebaseApp
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCrearTabla.setOnClickListener { irACrearTablaPadre() }
        btnListarListaPadre.setOnClickListener { irAListarTabla() }
    }

    fun irACrearTablaPadre(){

        val intent = Intent(this, CrearTablaActivity::class.java)
        startActivity(intent)
    }
    fun irAListarTabla(){
        val intent = Intent(this, ListarTablaPadreActivity::class.java)
        startActivity(intent)
    }
    override fun onBackPressed() {
        moveTaskToBack(true)
    }
}
