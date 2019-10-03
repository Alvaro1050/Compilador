/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones;

import java.util.ArrayList;
import java.util.List;
import co.edu.eam.tlf.analizadorsintactico.sentencias.definicion.Sentencia;

/**
 *
 * @author alvar
 */
public class ExpresionCadena extends Sentencia {

    private Expresion expresion;

    public ExpresionCadena(Expresion expresion) {
        this.expresion = expresion;
    }

    public ExpresionCadena() {

    }

    public Expresion getExpresion() {
        return expresion;
    }

    public void setExpresion(Expresion expresion) {
        this.expresion = expresion;
    }

    @Override
    public List<co.edu.eam.tlf.analizadorsintactico.sentencias.definicion.Sentencia> llenarHijos() {
        hijos = new ArrayList<>();
        hijos.add(expresion);

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
