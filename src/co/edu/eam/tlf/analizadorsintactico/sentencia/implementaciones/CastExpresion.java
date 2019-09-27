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
 * @author Lenovo
 */
class CastExpresion extends Sentencia {

    private Expresion expresion;
    private Lexema tipo;

    public CastExpresion(Expresion expresion, Lexema tipo) {
        this.expresion = expresion;
        this.tipo = tipo;
    }

    public CastExpresion() {

    }

    public Expresion getExpresion() {
        return expresion;
    }

    public void setExpresion(Expresion expresion) {
        this.expresion = expresion;
    }

    public Lexema getTipo() {
        return tipo;
    }

    public void setTipo(Lexema tipo) {
        this.tipo = tipo;
    }

    @Override
    public List<Sentencia> llenarHijos() {

        hijos = new ArrayList<>();

        hijos.add(expresion);
        hijos.add(new SentenciaToken(tipo));

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
