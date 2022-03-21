package com.example.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

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
        var inflador = LayoutInflater.from(context)
        var vista = inflador.inflate(R.layout.note_layout, null)
        var nota = notas[p0]

        vista.tv_contenido_det


        nota.contenido

        return vista
    }

}