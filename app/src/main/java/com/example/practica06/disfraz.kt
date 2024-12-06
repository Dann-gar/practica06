package com.example.practica06

import android.os.Parcel
import android.os.Parcelable

data class disfraz(
    val nombre: String,
    val domicilio: String,
    val producto: String,
    val talla: String,
    val telefono: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeString(domicilio)
        parcel.writeString(producto)
        parcel.writeString(talla)
        parcel.writeString(telefono)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<disfraz> {
        override fun createFromParcel(parcel: Parcel): disfraz {
            return disfraz(parcel)
        }

        override fun newArray(size: Int): Array<disfraz?> {
            return arrayOfNulls(size)
        }
    }
}
