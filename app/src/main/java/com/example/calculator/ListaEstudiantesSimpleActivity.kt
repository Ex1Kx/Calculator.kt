package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListaEstudiantesSimpleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_estudiantes_simple)
        var recycler=findViewById<RecyclerView>(R.id.recyclerEstudiantes)
        recycler.adapter=AdapterEstudiante(Operaciones.listaEstudiantesStatic)
        recycler.layoutManager= LinearLayoutManager(this)
    }
}