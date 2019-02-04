package com.example.examen

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_crear_tabla.*
import org.json.JSONObject
import java.util.HashMap

class CrearTablaActivity : AppCompatActivity() {


    private var etNombreEquipo:EditText?=null
    private var etNombreLiga:EditText?=null
    private var etFechaCreacion:EditText?=null
    private var etCopasInter:EditText?=null
    private var etCampeonActual:EditText?=null

    private val URL = "http://192.168.1.128/webservices/equipo.php"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_tabla)

        etNombreEquipo = findViewById(R.id.etNombreEquipo) as EditText
        etNombreLiga = findViewById(R.id.etNombreLiga) as EditText
        etFechaCreacion = findViewById(R.id.etFechaCreacion) as EditText
        etCopasInter = findViewById(R.id.etCopasInter) as EditText
        etCampeonActual = findViewById(R.id.etCampeonActual) as EditText

        btnCancelar.setOnClickListener { irAMain() }
        btnGuardar.setOnClickListener { ejecutarServicio()}
    }

    fun irAMain(){


        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun ejecutarServicio(){

        var req = object:StringRequest(Request.Method.POST,URL,
            Response.Listener<String> {
                response ->
                Toast.makeText(applicationContext, "PERFECTOOOOOOO!", Toast.LENGTH_SHORT).show()



            }, object: Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {

                    Toast.makeText(applicationContext, "Todo Mal", Toast.LENGTH_SHORT).show()
                }

            }){
                override fun getParams():MutableMap<String,String>{

                val params=HashMap<String,String>()
                    params.put("NombreEquipo",etNombreEquipo?.text.toString())
                    params.put("NombreLiga",etNombreLiga?.text.toString())
                    params.put("FechaCreacion",etFechaCreacion?.text.toString())
                    params.put("NumeroCopas",etCopasInter?.text.toString())
                    params.put("CampeonActual",etCampeonActual?.text.toString())
                    return params

            }
        }
            VolleySingleton.instance?.addToRequestQueue(req)
        }
}
