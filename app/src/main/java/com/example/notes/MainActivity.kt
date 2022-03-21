package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.ListView
import java.io.*

class MainActivity : AppCompatActivity() {
    var notas =ArrayList<Nota>()
    lateinit var adaptador: AdaptadorNotas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_view_layout)

         val button = findViewById(R.id.btn_guardar) as Button

        button.setOnClickListener{
            var intent = Intent(this, AgregarNotaActivity::class.java)
            startActivityForResult(intent, 123)
        }

        val listview = findViewById<ListView>(R.id.Listview)

        adaptador = AdaptadorNotas(this, notas)
        listview.adapter = adaptador
    }

    fun leerNotas(){
        notas.clear()
        var carpeta = File(ubicacion().absolutePath)

        if(carpeta.exists()){
            var archivos = carpeta.listFiles()
            if(archivos != null){
                for (archivo in archivos){
                    leerArchivo(archivo)
                }
            }
        }

    }

    private fun ubicacion(): File{
        val folder = File(Environment.getExternalStorageDirectory(), "notas")
        if(!folder.exists()){
            folder.mkdir()
        }
        return folder
    }

    fun leerArchivo(archivo: File){
        val fis = FileInputStream(archivo)
        val di = DataInputStream(fis)
        val br = BufferedReader(InputStreamReader(di))
        var strLine: String? = br.readLine()
        var myData =""

        while(strLine != null){
            myData = myData + strLine
            strLine = br.readLine()
        }
        br.close()
        di.close()
        fis.close()

        var nombre = archivo.name.substring(0, archivo.name.length-4)
        var nota = Nota(nombre ,myData)
        notas.add(nota)

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 123){
            leerNotas()
            adaptador.notifyDataSetChanged()
        }
    }

}