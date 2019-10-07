/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones;

import co.edu.eam.tlf.analizadorlexico.modelo.Lexema;
import co.edu.eam.tlf.analizadorsintactico.sentencias.definicion.Sentencia;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alvar
 */
public class Argumento extends Sentencia {

    private Lexema nombre;

    public Argumento() {
    }

    public Argumento(Lexema nombre) {
        this.nombre = nombre;
    }

    public Lexema getNombre() {
        return nombre;
    }

    public void setNombre(Lexema nombre) {
        this.nombre = nombre;
    }

    @Override
    public List<Sentencia> llenarHijos() {
        hijos = new ArrayList<>();

        hijos.add(new SentenciaToken(nombre));

        return hijos;

    }

    @Override
    public String toString() {
        return "Argumento:  " + nombre.getToken() + "En la fila: " + nombre.getFila() + " y columna: " + nombre.getColumna();

    }

    @Override
    public String parse() {
        StringBuilder str = new StringBuilder();
        str.append("Argumento ");

        str.append("con nombre ").append(nombre.getToken());

        return str.toString();
    }

}
