package com.example.calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import java.sql.DriverManager.println

class MainActivity : AppCompatActivity() {
    var operaciones: Operaciones?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        operaciones = Operaciones()
        iniciarComponentes()
    }
    private val response=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ valor ->
        if (valor.resultCode== RESULT_OK){
            //resp y resp 2 almacenan el mismo dato, solo se muestran 2 formas como podria capturarse el dato
            val resp=valor?.data?.extras?.get("resultado") as String
            val resp2=valor?.data?.getStringExtra("resultado")
            println("Valor respuesta=$resp y la resps2=$resp2")
            //capturamos el objeto nuevo y lo asignamos a operaciones
            operaciones= valor?.data?.extras?.get("objetoOperaciones") as Operaciones?
            println("INFORMACION LISTA ESTATICA")
            println("Tamaño Lista Estatica: ${Operaciones.listaEstudiantesStatic.size}")
            for(est in Operaciones.listaEstudiantesStatic){
                println(est)
            }
        }
    }

    private fun iniciarComponentes() {

        val botonRegistro: Button =findViewById(R.id.btnCalcular)
        val botonEstadisticas:Button=findViewById(R.id.btnEstadisticas)
        val botonAyuda:Button=findViewById(R.id.btnAyuda)

        botonRegistro.setOnClickListener { onClick(1) }
        botonAyuda.setOnClickListener { onClick(2) }
        botonEstadisticas.setOnClickListener { onClick(3) }
    }

    private fun onClick(boton: Int) {

        when (boton) {
            1 -> {
                var miIntent: Intent = Intent(this, Registro::class.java)
                var miBundle: Bundle = Bundle()
                miBundle.putSerializable("operaciones", operaciones)
                miBundle.putString("Nombre", "Pepe")
                miIntent.putExtras(miBundle)
                miIntent.putExtra("Objeto", operaciones)
                // startActivity(miIntent)
                response.launch(miIntent)
            }
            2 -> startActivity(Intent(this, Ayuda::class.java))
            3 -> startActivity(Intent(this, ListaEstudiantesSimpleActivity::class.java))
        }
    }
}