package com.alejandro.alvarez.cazarpatos

import android.app.Activity
import android.content.Context
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class InternalFileManager(val actividad: Activity) : FileHandler {

    private val fileName = "user_data.txt"

    override fun SaveInformation(datosAGrabar: Pair<String, String>) {
        try {
            val outputStreamWriter = OutputStreamWriter(
                actividad.openFileOutput(fileName, Context.MODE_PRIVATE)
            )
            outputStreamWriter.write("${datosAGrabar.first}|${datosAGrabar.second}")
            outputStreamWriter.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun ReadInformation(): Pair<String, String> {
        var email = ""
        var password = ""

        try {
            val inputStream = actividad.openFileInput(fileName)
            val inputStreamReader = InputStreamReader(inputStream)
            val bufferedReader = BufferedReader(inputStreamReader)

            val data = bufferedReader.readLine()
            bufferedReader.close()

            if (!data.isNullOrEmpty()) {
                val parts = data.split("|")
                if (parts.size == 2) {
                    email = parts[0]
                    password = parts[1]
                }
            }
        } catch (e: FileNotFoundException) {
            // Archivo no existe, devolver valores vacíos
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return (email to password)
    }
}