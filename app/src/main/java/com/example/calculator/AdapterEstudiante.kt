package com.example.calculator

import android.content.Intent
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
                resultado.text="El Estudiante Ha Logrado Ganar La Materia"
            else if (datoEst.promedio == 3.5)
                resultado.text="El Estudiante Ha Perdido Pero Puede Recuperar"
            else if (datoEst.promedio<3.5)
                resultado.text="El Estudiante Ha Perdido La Materia Sin Posibilidad De Recuperar"
            else if (datoEst.promedio<0)
                resultado.text="Error Los Resultados No Pueden Ser Menores Que Cero"
            else if (datoEst.promedio>5.0)
                resultado.text="Error Los Resultados No Pueden Ser Mayores A Cinco"
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterEstudiante.ViewHolderEstudiante {
        var layoutInflater= LayoutInflater.from(parent.context)
        return ViewHolderEstudiante(layoutInflater.inflate(R.layout.item_list,parent,false))
    }
    override fun onBindViewHolder(holder: AdapterEstudiante.ViewHolderEstudiante, position: Int) {
        holder.asignarDatos(estudiantesList.get(position))
    }
    override fun getItemCount(): Int {
        return estudiantesList.size
    }

}