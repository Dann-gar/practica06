package com.example.practica06

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var pedido: Button
    private lateinit var productos: Button
    private lateinit var compras: Button
    private lateinit var nosotros: Button
    private lateinit var salir: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        val username = intent.getStringExtra("User")  ?: ""
        if (username.isEmpty() ) {
            startActivity(Intent(this, LoginsActivity::class.java))

        }else {

            setContentView(R.layout.activity_main)
            pedido = findViewById(R.id.buttonpedido)

            compras = findViewById(R.id.buttoncompras)
            nosotros = findViewById(R.id.Buttonnosotros)
            salir = findViewById(R.id.Salir)

            pedido.setOnClickListener{

                startActivity(Intent(this, pedidos::class.java))
            }

            compras.setOnClickListener{
                val intent = Intent(this, MisComprasActivity::class.java)

                startActivity(intent)

            }
            nosotros.setOnClickListener{
                         startActivity(Intent(this,  ContactActivity::class.java))

            }
            salir.setOnClickListener{
                startActivity(Intent(this, LoginsActivity::class.java))

            }
        }



        }
    }
