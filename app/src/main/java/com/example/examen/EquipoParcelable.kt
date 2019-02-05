package com.example.examen

import android.os.Parcel
import android.os.Parcelable

data class EquipoParcelable (val IdEquipo: String,
                             val NombreEquipo: String,
                             val NombreLiga: String,
                             val FechaCreacion: String,
                             val NumeroCopas: String,
                             val CampeonActual:String):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(IdEquipo)
        parcel.writeString(NombreEquipo)
        parcel.writeString(NombreLiga)
        parcel.writeString(FechaCreacion)
        parcel.writeString(NumeroCopas)
        parcel.writeString(CampeonActual)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EquipoParcelable> {
        override fun createFromParcel(parcel: Parcel): EquipoParcelable {
            return EquipoParcelable(parcel)
        }

        override fun newArray(size: Int): Array<EquipoParcelable?> {
            return arrayOfNulls(size)
        }
    }
}