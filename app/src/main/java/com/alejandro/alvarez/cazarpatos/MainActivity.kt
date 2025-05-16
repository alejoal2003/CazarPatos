package com.alejandro.alvarez.cazarpatos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // No es necesario agregar más código ya que solo necesitamos mostrar el mensaje
        // El mensaje ya está definido en el layout XML
    }
}