package com.alejandro.alvarez.cazarpatos

import android.app.Activity
import android.os.Environment
import android.util.Log
import java.io.BufferedReader
import java.io.File
import java.io.FileNotFoundException
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException

class ExternalFileManager(val actividad: Activity) : FileHandler {

    private val fileName = "user_data_external.txt"
    private val TAG = "ExternalFileManager"

    private fun isExternalStorageWritable(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }

    private fun getExternalFile(): File {
        // Almacenamiento externo privado - NO requiere permisos
        val externalDir = actividad.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        val file = File(externalDir, fileName)
        Log.d(TAG, "Archivo ubicado en: ${file.absolutePath}")
        return file
    }

    override fun SaveInformation(datosAGrabar: Pair<String, String>) {
        if (!isExternalStorageWritable()) {
            Log.e(TAG, "Almacenamiento externo no disponible")
            return
        }

        try {
            val file = getExternalFile()
            val fileWriter = FileWriter(file)
            fileWriter.write("${datosAGrabar.first}|${datosAGrabar.second}")
            fileWriter.close()
            Log.d(TAG, "✅ Datos guardados en: ${file.absolutePath}")
            Log.d(TAG, "📁 Datos: ${datosAGrabar.first} | ${datosAGrabar.second}")
        } catch (e: IOException) {
            Log.e(TAG, "❌ Error al guardar: ${e.message}")
            e.printStackTrace()
        }
    }

    override fun ReadInformation(): Pair<String, String> {
        var email = ""
        var password = ""

        if (!isExternalStorageWritable()) {
            Log.e(TAG, "Almacenamiento externo no disponible para lectura")
            return (email to password)
        }

        try {
            val file = getExternalFile()
            if (file.exists()) {
                val bufferedReader = BufferedReader(FileReader(file))
                val data = bufferedReader.readLine()
                bufferedReader.close()

                if (!data.isNullOrEmpty()) {
                    val parts = data.split("|")
                    if (parts.size == 2) {
                        email = parts[0]
                        password = parts[1]
                    }
                }
                Log.d(TAG, "✅ Datos leídos desde: ${file.absolutePath}")
                Log.d(TAG, "📖 Email: $email, Password length: ${password.length}")
            } else {
                Log.d(TAG, "ℹ️ Archivo no existe: ${file.absolutePath}")
            }
        } catch (e: FileNotFoundException) {
            Log.d(TAG, "ℹ️ Archivo no encontrado")
        } catch (e: IOException) {
            Log.e(TAG, "❌ Error al leer: ${e.message}")
            e.printStackTrace()
        }

        return (email to password)
    }
}