package com.example.practica06

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var etUser: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnRegister: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        etUser = findViewById(R.id.editTextRegisterUser)
        etPassword = findViewById(R.id.editTextRegisterPassword)
        btnRegister = findViewById(R.id.buttonRegister)
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        btnRegister.setOnClickListener {
            val username = etUser.text.toString()
            val password = etPassword.text.toString()


            if (username.isNotEmpty() && password.isNotEmpty()) {
                val editor = sharedPreferences.edit()
                editor.putString("registered_user", username)
                editor.putString("registered_password", password)

                editor.apply()

                Toast.makeText(this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginsActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

    }
}

