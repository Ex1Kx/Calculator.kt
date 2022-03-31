package com.example.calculator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterEstudiante(val estudiantesList:ArrayList<Estudiante>): RecyclerView.Adapter<AdapterEstudiante.ViewHolderEstudiante>(){
    //Esta clase interna representa la clase que mapeará los elementos de la lista en cada item del recycler
    class ViewHolderEstudiante(view: View):RecyclerView.ViewHolder(view) {
        var nombre: TextView =view.findViewById(R.id.idNombre)
        var promedio:TextView=view.findViewById(R.id.idPromedio)
        var resultado:TextView=view.findViewById(R.id.idResultado)

        fun asignarDatos(datoEst: Estudiante) {
            nombre.text=datoEst.nombre
            promedio.text=datoEst.promedio.toString()

            if (datoEst.promedio>3.5)
                resultado.text="Gana"
            else
                resultado.text="Pierde"
        }

    }
    //Esta función infla el xml de los items
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterEstudiante.ViewHolderEstudiante {
        var layoutInflater= LayoutInflater.from(parent.context)
        return ViewHolderEstudiante(layoutInflater.inflate(R.layout.item_list,parent,false))
    }
    //Este metodo obtiene cada elemento de la lista y se envia a la clase holder
    override fun onBindViewHolder(holder: AdapterEstudiante.ViewHolderEstudiante, position: Int) {
        holder.asignarDatos(estudiantesList.get(position))
    }

    //Define el tamao del modelo de datos
    override fun getItemCount(): Int {
        return estudiantesList.size
    }

}