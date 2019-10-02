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
public class Para extends Sentencia {

    private DeclaradorVariable declaradorVariable;
    private Expresion expresion;
    private ExpresionLogica expresionLogica;
    private Lista<Sentencia> listaSentencia;

    public Para(DeclaradorVariable declaradorVariable, Expresion expresion, ExpresionLogica expresionLogica, Lista<Sentencia> listaSentencia) {
        this.declaradorVariable = declaradorVariable;
        this.expresion = expresion;
        this.expresionLogica = expresionLogica;
        this.listaSentencia = listaSentencia;
    }

    public Para() {
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

    public Lista<Sentencia> getListaSentencia() {
        return listaSentencia;
    }

    public void setListaSentencia(Lista<Sentencia> listaSentencia) {
        this.listaSentencia = listaSentencia;
    }

    @Override
    public List<Sentencia> llenarHijos() {
        hijos = new ArrayList<>();
        hijos.add(expresion);
        hijos.add(expresionLogica);
        hijos.add(listaSentencia);
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
