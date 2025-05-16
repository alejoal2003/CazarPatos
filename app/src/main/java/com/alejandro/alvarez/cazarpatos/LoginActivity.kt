package com.alejandro.alvarez.cazarpatos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Obtener referencia al botón de login
        val loginButton = findViewById<Button>(R.id.loginButton)

        // Configurar el evento de clic para el botón de login
        loginButton.setOnClickListener {
            // Crear intent para navegar a MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}