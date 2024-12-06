package com.example.practica06

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {
    class prDisfraz(
        val nombre: String,
        val descripcion: String,
        val tallas: List<String>,
        val costo: Double
    )


    private lateinit var listView: ListView
    private lateinit var disfraces: List<prDisfraz>
    private lateinit var buttonCancelar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productos)

        buttonCancelar = findViewById(R.id.Cancelarbtn)
        buttonCancelar.setOnClickListener {
            finish()  // Cerrar la Activity y volver a la Activity anterior
        }
        // Inicializar el ListView
        listView = findViewById(R.id.listViewDisfraces)
        // Crear una lista de disfraces (esto podría venir de una base de datos o API)
        val productos = arrayOf(
            "Disfraz de Pirata",
            "Disfraz de Superhéroe",
            "Disfraz de Princesa",
            "Disfraz de Dinosaurio",
            "Disfraz de Robot"
        )

        // Crear una lista de disfraces con descripción, tallas y costo
        disfraces = listOf(
            prDisfraz("Disfraz de Pirata", "Disfraz clásico de pirata.", listOf("Chico", "Mediano", "Grande"), 29.99),
            prDisfraz("Disfraz de Superhéroe", "Disfraz de un famoso superhéroe.", listOf("Chico", "Mediano", "Grande"), 39.99),
            prDisfraz("Disfraz de Princesa", "Disfraz de una hermosa princesa.", listOf("Chico", "Mediano", "Grande"), 34.99),
            prDisfraz("Disfraz de Dinosaurio", "Disfraz divertido de dinosaurio.", listOf("Chico", "Mediano", "Grande"), 44.99),
            prDisfraz("Disfraz de Robot", "Disfraz futurista de robot.", listOf("Chico", "Mediano", "Grande"), 49.99)
        )

        // Configurar el adaptador para el ListView
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, productos)
        listView.adapter = adapter

        // Manejar el clic en un elemento de la lista
        listView.setOnItemClickListener { _, _, position, _ ->
            val disfrazSeleccionado = disfraces[position]
            // Mostrar información del disfraz
            val mensaje = "Descripción: ${disfrazSeleccionado.descripcion}\n" +
                    "Tallas: ${disfrazSeleccionado.tallas.joinToString(", ")}\n" +
                    "Costo: $${disfrazSeleccionado.costo}"
            Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
        }
    }
}