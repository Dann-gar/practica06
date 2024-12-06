package com.example.practica06

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class LoginsActivity : AppCompatActivity() {

    private lateinit var etUsuario: EditText
    private lateinit var etContrasena: EditText
    private lateinit var btnIngresar: Button
    private lateinit var btnSalir: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.logins)
        val btnGoToRegister: Button = findViewById(R.id.buttonGoToRegister)
        btnGoToRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        etUsuario = findViewById(R.id.editTextuser)
        etContrasena = findViewById(R.id.editTextTextPassword)
        btnIngresar = findViewById(R.id.login)
        btnSalir = findViewById(R.id.exit)

        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        btnIngresar.setOnClickListener {
            val usuario = etUsuario.text.toString()
            val contrasena = etContrasena.text.toString()

            // Credenciales predefinidas para el administrador
            val adminUser = "admin"
            val adminPassword = "admin123"

            // Credenciales registradas por el usuario
            val registeredUser = sharedPreferences.getString("registered_user", null)
            val registeredPassword = sharedPreferences.getString("registered_password", null)

            if (usuario == adminUser && contrasena == adminPassword) {
                // Ir a la actividad de administrador
                val intent = Intent(this, AdminActivity::class.java)
                startActivity(intent)
                finish()
            } else if (usuario == registeredUser && contrasena == registeredPassword) {
                // Ir a la actividad principal para usuarios normales
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("User", usuario)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }

        btnSalir.setOnClickListener {
           finish()


            System.exit(0)
        }
    }
}