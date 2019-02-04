package com.example.examen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import kotlinx.android.synthetic.main.activity_crear_tabla.*


class CrearTablaActivity : AppCompatActivity() {


    private var etNombreEquipo:EditText?=null
    private var etNombreLiga:EditText?=null
    private var etFechaCreacion:EditText?=null
    private var etCopasInter:EditText?=null
    private var etCampeonActual:EditText?=null

    val url="http://192.168.1.128:1337/Equipo"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_tabla)


/*
        url.httpGet()
            .responseString { request, response, result ->

                when (result) {
                    is com.github.kittinunf.result.Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http","Error: ${ex}")
                    }
                    is com.github.kittinunf.result.Result.Success -> {
                        val data = result.get()
                        Log.i("http","Datos: ${data}")

                    }
                }

            }
*/
        etNombreEquipo = findViewById(R.id.etNombreEquipo) as EditText
        etNombreLiga = findViewById(R.id.etNombreLiga) as EditText
        etFechaCreacion = findViewById(R.id.etFechaCreacion) as EditText
        etCopasInter = findViewById(R.id.etCopasInter) as EditText
        etCampeonActual = findViewById(R.id.etCampeonActual) as EditText

        btnCancelar.setOnClickListener { irAMain() }
        btnGuardar.setOnClickListener {crearTabla()}
    }

    fun irAMain(){


        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun crearTabla(){

        var parametros=listOf(
            "NombreEquipo" to etNombreEquipo?.text.toString(),
            "NombreLiga" to etNombreLiga?.text.toString(),
            "FechaCreacion" to etFechaCreacion?.text.toString(),
            "NumeroCopas" to etCopasInter?.text.toString(),
            "CampeonActual" to etCampeonActual?.text.toString()

        )

        url.httpPost(parametros)
            .responseString { request, response, result ->

                when (result) {
                    is com.github.kittinunf.result.Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http","Error: ${ex}")
                    }
                    is com.github.kittinunf.result.Result.Success -> {
                        val data = result.get()
                        val EquipoClase:EquipoHttp = Klaxon().parse<EquipoHttp>(data) as EquipoHttp
                        Log.i("http","Datos: ${EquipoClase?.NombreEquipo}")

                    }
                }

            }


    }

}
class EquipoHttp(var NombreEquipo:String,
                 var NombreLiga:String,
                 var FechaCreacion:String,
                 var NumeroCopas:String,
                 var CampeonActual:String,
                 var createAt:Long,
                 var updatedAt:Long,
                 var id:Int
){}