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
    private ExpresionNumerica expresionNumerica;

    private Lista<Sentencia> listaSentencia;

    public Para(DeclaradorVariable declaradorVariable, Expresion expresion, ExpresionLogica expresionLogica, Lista<Sentencia> listaSentencia) {
        this.declaradorVariable = declaradorVariable;
        this.expresion = expresion;
        this.expresionLogica = expresionLogica;
        this.listaSentencia = listaSentencia;
    }

    public ExpresionNumerica getExpresionNumerica() {
        return expresionNumerica;
    }

    public void setExpresionNumerica(ExpresionNumerica expresionNumerica) {
        this.expresionNumerica = expresionNumerica;
    }

    public Para() {
        listaSentencia = new Lista<>();
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
        if (expresion != null) {
            hijos.add(expresion);
        }

        if (expresionLogica != null) {
            hijos.add(expresionLogica);
        }
        if (declaradorVariable != null) {
            hijos.add(declaradorVariable);
        }
        if (expresionNumerica != null) {
            hijos.add(expresionNumerica);
        }

        if (!listaSentencia.getSentencias().isEmpty()) {
            hijos.add(listaSentencia);

        }
        return hijos;

    }

    @Override
    public String parse() {

        StringBuilder str = new StringBuilder();

        str.append("para").append("(");
        if (declaradorVariable != null) {
            str.append(declaradorVariable.parse());

        } else {
            str.append(expresion.parse());
        }

        str.append(";");

        str.append(expresionLogica.parse());
        str.append(";");

        str.append(expresionNumerica.parse());
        str.append(")");
        str.append("{");

        for (Sentencia sentencia : listaSentencia.getSentencias()) {
            str.append(sentencia.parse());
        }
        str.append("}");

        return str.toString();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("para");

        if (expresion != null) {
            str.append("con expresion ").append(expresion.parse());

        } else {
            str.append("con declaracion de variable ").append(declaradorVariable.parse());
        }
        str.append("con condicion ").append(expresionLogica.parse());

        str.append("con expresion numerica ").append(expresionNumerica.parse());

        str.append(" y sentencias:  ").append(listaSentencia.parse());

        return str.toString();
    }

}
