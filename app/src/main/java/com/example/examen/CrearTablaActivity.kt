package com.example.examen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_crear_tabla.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton


class CrearTablaActivity : AppCompatActivity() {

    lateinit var ref:DatabaseReference
    lateinit var listaequipos:MutableList<Equipo>
    private var etNombreEquipo:EditText?=null
    private var etNombreLiga:EditText?=null
    private var etFechaCreacion:EditText?=null
    private var etCopasInter:EditText?=null
    private var etCampeonActual:EditText?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_tabla)

        btnCancelar.setOnClickListener { irAMain() }
        btnGuardar.setOnClickListener {crearTabla()}
    }

    fun irAMain(){


        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun crearTabla() {



        val intent = Intent(this, MainActivity::class.java)

        alert("Desea guardar los datos?") {

            positiveButton ("De Acuerdo!"){ etNombreEquipo = findViewById(R.id.etNombreEquipo) as EditText
                etNombreLiga = findViewById(R.id.etNombreLiga) as EditText
                etFechaCreacion = findViewById(R.id.etFechaCreacion) as EditText
                etCopasInter = findViewById(R.id.etCopasInter) as EditText
                etCampeonActual = findViewById(R.id.etCampeonActual) as EditText


                val database = FirebaseDatabase.getInstance()
                val ref = database.getReference("Equipo")

                val equipoid=ref.push().key
                val equipo = Equipo(equipoid.toString(),etNombreEquipo?.text.toString(),etNombreLiga?.text.toString(),etFechaCreacion?.text.toString(),
                    etCopasInter?.text.toString(),etCampeonActual?.text.toString())


                ref.child(equipoid!!).setValue(equipo).addOnCompleteListener{

                    listaequipos= mutableListOf()

                    ref.addValueEventListener(object :ValueEventListener{

                        override fun onCancelled(p0: DatabaseError) {

                        }

                        override fun onDataChange(p0: DataSnapshot) {
                            if(p0!!.exists()){
                                listaequipos.clear()
                                for(h in p0.children){
                                    val equipo=h.getValue(Equipo::class.java)
                                    listaequipos.add(equipo!!)
                                    Intent(applicationContext, ListarTablaPadreActivity::class.java)
                                    val bundle = Bundle()
                                    bundle.putParcelableArrayList("ListaObjetos",listaequipos )
                                    intent.putExtras(bundle)
                                    startActivity(intent)

                                }

                            }
                        }
                    })
                    Toast.makeText(applicationContext,"Agregado Exitosamente",Toast.LENGTH_SHORT).show()

                    startActivity(intent)

                }}
            negativeButton("No aun!") { }
        }.show()



    }
}
