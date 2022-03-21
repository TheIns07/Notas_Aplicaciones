package com.example.notes

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File
import java.io.FileOutputStream

class AgregarNotaActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_nota)

        val btn_guardar = findViewById<Button>(R.id.btn_guardar)

        btn_guardar.setOnClickListener{
            guardarNota();
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode){

            235 -> {
                if((grantResults.isNotEmpty()&& grantResults[0] == PackageManager.PERMISSION_GRANTED)){
                    guardar()
                }
                else {
                    Toast.makeText(this, "Error: Permisos Denegados", Toast.LENGTH_SHORT).show()

                }

            }

        }
    }


    fun guardarNota(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        != PackageManager.PERMISSION_GRANTED
        ){
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                235
            )

        }
        else {
            guardar()
        }

    }

    fun guardar(){
        var titulo = findViewById<EditText>(R.id.et_titulo)
        var contenido = findViewById<EditText>(R.id.et_contenido)

        if(titulo.text.toString() == "" || contenido.text.toString() == "" ){
            Toast.makeText(this, "Error: Campo Vacio", Toast.LENGTH_SHORT).show()
        }
        else {
            try{
                val archivo = File(ubicacion(), titulo.text.toString() + ".txt")
                val fos = FileOutputStream(archivo)
                fos.write(contenido.text.toString().toByteArray())
                fos.close()
                Toast.makeText(
                    this,
                    "Archivo guardao en carpeta",
                    Toast.LENGTH_SHORT

                ).show()

            }catch(e: Exception){
                Toast.makeText(this, "Error: No se guardo el archivo", Toast.LENGTH_SHORT).show()

            }
        }
        finish()
    }

    fun ubicacion(): String{
    val carpeta = File(Environment.getExternalStorageDirectory(), "notas")

        if(!carpeta.exists()){
            carpeta.mkdir()
        }
        return carpeta.absolutePath
    }
}