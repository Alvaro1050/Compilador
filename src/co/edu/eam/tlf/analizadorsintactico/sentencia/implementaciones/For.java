/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones;

import co.edu.eam.tlf.analizadorsintactico.sentencias.definicion.Sentencia;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class For extends Sentencia {

    private DeclaradorVariable declaradorVariable;
    private Expresion expresion;
    private ExpresionLogica expresionLogica;
    private BloqueSentencia bloqueSentencia;

    public For(DeclaradorVariable declaradorVariable, Expresion expresion, ExpresionLogica expresionLogica, BloqueSentencia bloqueSentencia) {
        this.declaradorVariable = declaradorVariable;
        this.expresion = expresion;
        this.expresionLogica = expresionLogica;
        this.bloqueSentencia = bloqueSentencia;
    }

    public For() {

    }

    public DeclaradorVariable getDeclaradorVariable() {
        return declaradorVariable;
    }

    public void setDeclaradorVariable(DeclaradorVariable declaradorVariable) {
        this.declaradorVariable = declaradorVariable;
    }

    public Expresion getExpresion() {
        return expresion;
    }

    public void setExpresion(Expresion expresion) {
        this.expresion = expresion;
    }

    public ExpresionLogica getExpresionLogica() {
        return expresionLogica;
    }

    public void setExpresionLogica(ExpresionLogica expresionLogica) {
        this.expresionLogica = expresionLogica;
    }

    public BloqueSentencia getBloqueSentencia() {
        return bloqueSentencia;
    }

    public void setBloqueSentencia(BloqueSentencia bloqueSentencia) {
        this.bloqueSentencia = bloqueSentencia;
    }

    @Override
    public List<Sentencia> llenarHijos() {
        hijos = new ArrayList<>();
        hijos.add(expresion);
        hijos.add(expresionLogica);
        hijos.add(bloqueSentencia);
        hijos.add(declaradorVariable);
       
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
