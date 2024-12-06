package com.example.practica06

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ContactActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etMessage: EditText
    private lateinit var btnSend: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        // Inicializar las vistas
        etName = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)
        etMessage = findViewById(R.id.etMessage)
        btnSend = findViewById(R.id.btnSend)

        // Configurar el botón de enviar
        btnSend.setOnClickListener {
            val name = etName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val message = etMessage.text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || message.isEmpty()) {
                Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                // Simular el envío del mensaje
                Toast.makeText(
                    this,
                    "Mensaje enviado por $name\nCorreo: $email\nMensaje: $message",
                    Toast.LENGTH_LONG
                ).show()

                // Limpiar los campos después del envío
                etName.text.clear()
                etEmail.text.clear()
                etMessage.text.clear()
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("User", "usuario")
                startActivity(intent)
                finish()
            }
        }
    }
}
