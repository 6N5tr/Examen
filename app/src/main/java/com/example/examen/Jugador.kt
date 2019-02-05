package com.example.examen

class Jugador(
        val IdEquipo: String,
        val NombreEquipo: String,
        val NombreLiga: String,
        val FechaCreacion: String,
        val NumeroCopas: String,
        val CampeonActual:String
    ) {

        constructor() : this("", "", "", "", "", "")

}