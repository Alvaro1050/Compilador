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
public class Expresion2 extends Sentencia {

    private Lista<Argumento> listaArg;
    private Expresion expresion;
    private Lexema identificador;

    public Expresion2() {
        listaArg = new Lista<>();
    }

    public Lexema getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Lexema identificador) {
        this.identificador = identificador;
    }

    public Lista<Argumento> getListaArg() {
        return listaArg;
    }

    public void setListaArg(Lista<Argumento> listaArg) {
        this.listaArg = listaArg;
    }

    public Expresion getExpresion() {
        return expresion;
    }

    public void setExpresion(Expresion expresion) {
        this.expresion = expresion;
    }

    @Override
    public List<Sentencia> llenarHijos() {

        hijos = new ArrayList<>();
        if (expresion != null) {
            hijos.add(expresion);
        }
        if (identificador != null) {
            hijos.add(new SentenciaToken(identificador));
        }
        if (!listaArg.getSentencias().isEmpty()) {
            hijos.add(listaArg);
        }

        return hijos;

    }

    @Override
    public String toString() {
        return "Expresion";
    }

    @Override
    public String parse() {

        StringBuilder str = new StringBuilder();
        str.append(identificador);

        if (expresion != null) {

            str.append(expresion.parse());
        }

        for (Sentencia sentencia : listaArg.getSentencias()) {
            str.append(sentencia.parse());
        }

        return str.toString();
    }

}
