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
        hijos.add(expresion);
        hijos.add(new SentenciaToken(identificador));
        if (!listaArg.getSentencias().isEmpty()) {
            hijos.add(listaArg);
        }

        return hijos;

    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String parse() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
