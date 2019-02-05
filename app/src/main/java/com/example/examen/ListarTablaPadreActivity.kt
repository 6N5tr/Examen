package com.example.examen

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.View
import android.widget.AbsListView
import android.widget.ListView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_listar_tabla_padre.*
import org.jetbrains.anko.find

class ListarTablaPadreActivity : AppCompatActivity() {

    lateinit var ref:DatabaseReference
    lateinit var listaequipos:MutableList<Equipo>
    lateinit var listView:ListView
    lateinit var equille:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_tabla_padre)


        listaequipos= mutableListOf()
        ref=FirebaseDatabase.getInstance().getReference("Equipo")

        ref.addValueEventListener(object :ValueEventListener{

            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
               /* intent?.let {
                    val equipollega=intent.extras.getParcelable(CrearTablaActivity.EQUIPO) as EquipoParcelable
                    equille=equipollega.toString()
                    Log.i("Prueba",equille)
                }*/

                val nombre = intent.getStringExtra("nombre")

                if(p0!!.exists()){
                    listaequipos.clear()

                        listaequipos.add(nombre!!)

                        val adapter=EquipoAdapter(applicationContext,R.layout.equipos,listaequipos)

                        listView=findViewById(R.id.listviewpadre)
                        listView.adapter=adapter


                }
            }
        })



    }
    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu!!.setHeaderTitle("Select Option")
        menu.add(0, v!!.id, 0, "Editar")
        menu.add(0, v.id, 1, "Eliminar")
        menu.add(0, v.id, 2, "Listar Jugadores")
        menu.add(0, v.id, 3, "Compartir")
    }

}
