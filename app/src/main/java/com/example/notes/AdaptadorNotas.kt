package com.example.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView

class AdaptadorNotas: BaseAdapter {
    var context: Context
    var notas = ArrayList<Nota>()

    constructor(context: Context, notas: ArrayList<Nota>){
        this.context = context
        this.notas = notas
    }


    override fun getCount(): Int {
      return notas.size
    }

    override fun getItem(p0: Int): Any {
        return notas[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var inflador = LayoutInflater.from(context);
        var vista: View = inflador.inflate(R.layout.note_layout, null);
        var nota = notas[p0];

        val tv_titulo_det: TextView = vista.findViewById(R.id.tv_titulo_det);
        val tv_contenido_det: TextView = vista.findViewById(R.id.tv_contenido_det);

        tv_contenido_det.text = nota.contenido
        tv_titulo_det.text = nota.titulo

        return vista
    }

}