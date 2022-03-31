package com.example.calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Registro : AppCompatActivity() {
    var campoDocumento: EditText? = null
    var campoNombre: EditText? = null
    var campoEdad: EditText? = null
    var campoTelefono: EditText? = null
    var campoDireccion: EditText? = null

    var campoMateria1: EditText? = null
    var campoMateria2: EditText? = null
    var campoMateria3: EditText? = null
    var campoMateria4: EditText? = null
    var campoMateria5: EditText? = null
    var campoNota1: EditText? = null
    var campoNota2: EditText? = null
    var campoNota3: EditText? = null
    var campoNota4: EditText? = null
    var campoNota5: EditText? = null
    var operaciones: Operaciones? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        iniciarComponentes()

    }

    private fun iniciarComponentes() {

        var bundle: Bundle? = this.intent.extras
        operaciones = bundle?.getSerializable("operaciones") as Operaciones?
        campoDocumento = findViewById(R.id.campoDocumento)
        campoNombre = findViewById(R.id.campoNombre)
        campoEdad = findViewById(R.id.campoEdad)


        campoDocumento = findViewById(R.id.campoDocumento)
        campoNombre = findViewById(R.id.campoNombre)
        campoEdad = findViewById(R.id.campoEdad)
        campoTelefono = findViewById(R.id.campoTelefono)
        campoDireccion = findViewById(R.id.campoDireccion)

        campoMateria1 = findViewById(R.id.campoMateria1)
        campoMateria2 = findViewById(R.id.campoMateria2)
        campoMateria3 = findViewById(R.id.campoMateria3)
        campoMateria4 = findViewById(R.id.campoMateria4)
        campoMateria5 = findViewById(R.id.campoMateria5)

        campoNota1 = findViewById(R.id.campoNota1)
        campoNota2 = findViewById(R.id.campoNota2)
        campoNota3 = findViewById(R.id.campoNota3)
        campoNota4 = findViewById(R.id.campoNota4)
        campoNota5 = findViewById(R.id.campoNota5)

        var btnRegistro: Button = findViewById(R.id.btnRegistrar)
        btnRegistro.setOnClickListener { registrarEstudiante() }

    }

    private fun registrarEstudiante() {
        //Se instancia la clase estudiante y se mapean los elementos del formulario
        var est: Estudiante = Estudiante()
        est.documento = campoDocumento?.text.toString()
        est.nombre = campoNombre?.text.toString()
        est.edad = campoEdad?.text.toString().toInt()
        est.direccion = campoDireccion?.text.toString()
        est.telefono = campoTelefono?.text.toString()

        //Si se va a trabajar con un mapa para la gestión de notas
        est.mapaMaterias.put(
            campoMateria1?.text.toString(),
            Materia(campoMateria1?.text.toString(), campoNota1?.text.toString().toDouble())
        )

        //Si se va a trabajar las materias y notas en la misma clase Estudiante
        est.materia1 = campoMateria1?.text.toString()
        est.materia2 = campoMateria2?.text.toString()
        est.materia3 = campoMateria3?.text.toString()
        est.materia4 = campoMateria4?.text.toString()
        est.materia5 = campoMateria5?.text.toString()

        est.nota1 = campoNota1?.text.toString().toDouble()
        est.nota2 = campoNota2?.text.toString().toDouble()
        est.nota3 = campoNota3?.text.toString().toDouble()
        est.nota4 = campoNota4?.text.toString().toDouble()
        est.nota5 = campoNota5?.text.toString().toDouble()

        //Se asigna el promedio delegando el calculo al metodo de la clase operaciones
        est.promedio = operaciones!!.calcularPromedio(est)

        operaciones?.registrarEstudiante(est)

        operaciones?.imprimirListaEstudiantes()
    }

    private fun devolverResultados() {
        var miIntent: Intent = Intent()
        miIntent.putExtra("resultado", "Registro exitoso")
        var miBundle: Bundle = Bundle()
        miBundle.putSerializable("objetoOperaciones", operaciones)
        miIntent.putExtras(miBundle)
        //miIntent.putExtra("obj",operaciones)
        setResult(RESULT_OK, miIntent)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Toast.makeText(this, "Se cierra el registro Activity", Toast.LENGTH_SHORT).show()
            devolverResultados()
            finish()
        }
        return super.onKeyDown(keyCode, event)
    }
}