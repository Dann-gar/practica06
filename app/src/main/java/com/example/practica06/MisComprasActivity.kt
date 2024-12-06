package com.example.practica06

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MisComprasActivity : AppCompatActivity() {
    private lateinit var buttonCancelar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_miscompras)

        buttonCancelar = findViewById(R.id.exitt)
        buttonCancelar.setOnClickListener {
            finish() // Cierra la Activity
        }

        // Obtener todas las reservas guardadas
        val reservaManager = ReservaManager(this)
        val reservas = reservaManager.getReservas()

        // Mostrar todas las reservas en un TextView
        val textView: TextView = findViewById(R.id.textViewReserva)
        if (reservas.isNotEmpty()) {
            val reservasText = reservas.joinToString(separator = "\n\n") { reserva ->
                """
                Nombre: ${reserva.nombre}
                Domicilio: ${reserva.domicilio}
                Producto: ${reserva.producto}
                Talla: ${reserva.talla}
                Teléfono: ${reserva.telefono}
                Fecha: ${reserva.fecha}
                """.trimIndent()
            }
            textView.text = reservasText
        } else {
            textView.text = "No hay reservas registradas."
        }
    }
}
