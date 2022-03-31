package com.example.calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

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
            operaciones?.imprimirListaEstudiantes()
        }
    }

    private fun iniciarComponentes() {

        val botonRegistro: Button =findViewById(R.id.btnCalular)
        val botonEstadisticas:Button=findViewById(R.id.btnEstadisticas)
        val botonAyuda:Button=findViewById(R.id.btnAyuda)

        botonRegistro.setOnClickListener { onClick(1) }
        botonAyuda.setOnClickListener { onClick(2) }
        botonEstadisticas.setOnClickListener { onClick(3) }
    }

    private fun onClick(boton: Int) {

        when (boton) {
            1 -> {
                var miIntent: Intent = Intent(this, RegistroActivity::class.java)
                var miBundle: Bundle = Bundle()
                miBundle.putSerializable("operaciones", operaciones)
                miBundle.putString("Nombre", "Pepe")
                miIntent.putExtras(miBundle)
                miIntent.putExtra("Objeto", operaciones)
                // startActivity(miIntent)
                response.launch(miIntent)
            }
            2 -> startActivity(Intent(this, AyudaActivity::class.java))
            3 -> startActivity(Intent(this, EstadisticasActivity::class.java))
        }
    }
}