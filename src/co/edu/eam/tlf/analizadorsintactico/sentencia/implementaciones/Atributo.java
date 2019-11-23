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
public class Atributo extends Sentencia {

    /**
     * Nombre del atributo
     */
    private Lexema nombre;

    /**
     * Tipo de dato.
     */
    private Lexema tipoDato;

    private Lexema valor;

    public Atributo() {
    }

    @Override
    public List<Sentencia> llenarHijos() {
        hijos = new ArrayList<>();

        hijos.add(new SentenciaToken(tipoDato));
        hijos.add(new SentenciaToken(nombre));
        hijos.add(new SentenciaToken(valor));
        return hijos;
    }

    public Lexema getValor() {
        return valor;
    }

    public void setValor(Lexema valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Atributo:" + tipoDato.getToken() + "-" + nombre.getToken();
    }

    public Lexema getNombre() {
        return nombre;
    }

    public void setNombre(Lexema nombre) {
        this.nombre = nombre;
    }

    public Lexema getTipoDato() {
        return tipoDato;
    }

    public void setTipoDato(Lexema tipoDato) {
        this.tipoDato = tipoDato;
    }

    @Override
    public String parse() {
        StringBuilder str = new StringBuilder();

        str.append("public static ");
        if (tipoDato.getToken().equals("cadena")) {
            str.append("String").append(" ");

        } else if (tipoDato.getToken().equals("entero")) {
            str.append("int").append(" ");

        } else if (tipoDato.getToken().equals("boo")) {
            str.append("boolean").append(" ");
        } else if (tipoDato.getToken().equals("decimal")) {
            str.append("double").append(" ");
        } else if (tipoDato.getToken().equals("flotante")) {
            str.append("float").append(" ");
        }
        str.append(nombre.getToken());

        if (valor != null) {
            str.append(" = ").append(valor.getToken());
        }
        str.append(";");

        return str.toString();
    }

}
