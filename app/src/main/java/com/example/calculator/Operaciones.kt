package com.example.calculator

import java.io.Serializable

class Operaciones: Serializable {
    companion object{
    var listaEstudiantesStatic: ArrayList<Estudiante> =arrayListOf<Estudiante>()

    fun registrarEstudiante(estudiante: Estudiante){

        listaEstudiantesStatic.add(estudiante)
    }

    fun imprimirListaEstudiantes(){
        for(est in listaEstudiantesStatic){
            println(est)
        }
    }

    fun calcularPromedio(est: Estudiante): Double {

        var prom=(est.nota1+est.nota2+est.nota3+est.nota4+est.nota4+est.nota5)/5

        return prom
        }
    }
}