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
 * @author caferrerb
 */
public class Parametro extends Sentencia {

    /**
     * Nombre del parametro
     */
    private Lexema nombre;
    /**
     * Tipo de dato del parametro.
     */
    private Lexema tipo;

    int comas = 0;

    /**
     * COnstructor
     */
    public Parametro() {
    }

    /**
     * COnstructor.
     *
     * @param nombre
     * @param tipo
     */
    public Parametro(Lexema nombre, Lexema tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }

    @Override
    public List<Sentencia> llenarHijos() {

        hijos = new ArrayList<>();

        hijos.add(new SentenciaToken(tipo));
        hijos.add(new SentenciaToken(nombre));
        return hijos;
    }

    @Override
    public String toString() {

        return "Parametro:" + nombre.getToken();

    }

    @Override
    public String parse() {
        StringBuilder str = new StringBuilder();

        if (tipo.getToken().equals("cadena")) {
            str.append("String").append(" ");
        } else if (tipo.getToken().equals("entero")) {
            str.append("int").append(" ");
        } else if (tipo.getToken().equals("decimal")) {
            str.append("double").append(" ");
        } else if (tipo.getToken().equals("flotante")) {
            str.append("float").append(" ");
        } else if (tipo.getToken().equals("boo")) {
            str.append("boolean").append(" ");
        }

        str.append(nombre.getToken());
        return str.toString();
    }

}
