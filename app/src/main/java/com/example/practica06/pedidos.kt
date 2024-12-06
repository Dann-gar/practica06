package com.example.practica06

import Reserva
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import java.util.Calendar

class pedidos : AppCompatActivity() {
    private lateinit var editTextNombre: EditText
    private lateinit var editTextDomicilio: EditText
    private lateinit var spinnerProducto: Spinner
    private lateinit var spinnerTalla: Spinner
    private lateinit var editTextTelefono: EditText
    private lateinit var buttonRegistrar: Button
    private lateinit var buttonCancelar: Button
    private lateinit var eltPlannedDate: EditText
    private var fechaSeleccionada: String? = null // Variable para almacenar la fecha
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedidos)

        // Inicializar los elementos del layout
        editTextNombre = findViewById(R.id.editTextNombre)
        editTextDomicilio = findViewById(R.id.editTextDomicilio)
        spinnerProducto = findViewById(R.id.spinnerProducto)
        spinnerTalla = findViewById(R.id.spinnerTalla)
        editTextTelefono = findViewById(R.id.editTextTelefono)
        buttonRegistrar = findViewById(R.id.buttonRegistrar)
        buttonCancelar = findViewById(R.id.buttonCancelar)
        eltPlannedDate = findViewById(R.id.eltPlannedDate)

        val productos = arrayOf("1", "2", "3", "4", "5")
        val adapterProducto = ArrayAdapter(this, android.R.layout.simple_spinner_item, productos)
        adapterProducto.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerProducto.adapter = adapterProducto

        // Configurar el Spinner para tallas
        val tallas = arrayOf("1", "2", "3", "4", "5")
        val adapterTalla = ArrayAdapter(this, android.R.layout.simple_spinner_item, tallas)
        adapterTalla.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTalla.adapter = adapterTalla
        eltPlannedDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                val selectedCalendar = Calendar.getInstance()
                selectedCalendar.set(selectedYear, selectedMonth, selectedDay)

                val dayOfWeek = selectedCalendar.get(Calendar.DAY_OF_WEEK)
                if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
                    Toast.makeText(this, "No se permiten fines de semana.", Toast.LENGTH_SHORT).show()
                } else {
                    fechaSeleccionada = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                    eltPlannedDate.setText(fechaSeleccionada)
                }
            }, year, month, day)

            datePickerDialog.datePicker.minDate = calendar.timeInMillis
            datePickerDialog.show()
        }

        buttonRegistrar.setOnClickListener {
            val nombre = editTextNombre.text.toString()
            val domicilio = editTextDomicilio.text.toString()
            val producto = spinnerProducto.selectedItem.toString()
            val talla = spinnerTalla.selectedItem.toString()
            val telefono = editTextTelefono.text.toString()

            // Validar que la fecha esté seleccionada
            if (fechaSeleccionada == null) {
                Toast.makeText(this, "Por favor selecciona una fecha válida.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Crear una nueva instancia de Reserva
            val reserva = Reserva(nombre, domicilio, producto, talla, telefono, fechaSeleccionada!!)

            // Guardar la reserva usando ReservaManager
            val reservaManager = ReservaManager(this)
            reservaManager.addReserva(reserva)

            // Mostrar mensaje y redirigir a MisComprasActivity
            Toast.makeText(this, "Reserva registrada exitosamente.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MisComprasActivity::class.java)
            startActivity(intent)
        }



        buttonCancelar.setOnClickListener {
            finish()
        }
    }


}