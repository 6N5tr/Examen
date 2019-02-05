package com.example.examen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class JugadorAdapter(val mCtx:Context,val layoutResId:Int,val ListaEquipos:List<Equipo>)
    : ArrayAdapter<Equipo>(mCtx,layoutResId,ListaEquipos)
{

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater= LayoutInflater.from(mCtx)
        val view: View=layoutInflater.inflate(layoutResId,null)
        val textViewName=view.findViewById<TextView>(R.id.textViewName)
        val equipo=ListaEquipos[position]
        textViewName.text=equipo.NombreEquipo
        return view
    }
}