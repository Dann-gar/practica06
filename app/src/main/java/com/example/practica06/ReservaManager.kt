package com.example.practica06

import Reserva
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ReservaManager(context: Context) {

    private val sharedPreferences = context.getSharedPreferences("Reservas", Context.MODE_PRIVATE)
    private val gson = Gson()

    // Método para guardar una lista completa de reservas
    fun saveReservas(reservas: List<Reserva>) {
        val json = gson.toJson(reservas)
        sharedPreferences.edit().putString("RESERVAS_LIST", json).apply()
    }

    // Método para obtener todas las reservas
    fun getReservas(): MutableList<Reserva> {
        val json = sharedPreferences.getString("RESERVAS_LIST", null)
        val type = object : TypeToken<MutableList<Reserva>>() {}.type
        return if (json != null) gson.fromJson(json, type) else mutableListOf()
    }

    // Método para agregar una nueva reserva
    fun addReserva(reserva: Reserva) {
        val reservas = getReservas()
        reservas.add(reserva)
        saveReservas(reservas)
    }
}
