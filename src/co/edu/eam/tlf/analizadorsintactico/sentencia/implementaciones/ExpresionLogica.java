/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones;

import co.edu.eam.tlf.analizadorlexico.modelo.Lexema;
import java.util.ArrayList;
import java.util.List;
import co.edu.eam.tlf.analizadorsintactico.sentencias.definicion.Sentencia;

/**
 *
 * @author alvar
 */
public class ExpresionLogica extends Sentencia {

    private List<Expresion> expresiones;
    private List<Lexema> operadores;
    private List<Lexema> identificadores;

    public ExpresionLogica() {
        expresiones = new ArrayList<>();
        operadores = new ArrayList<>();
        identificadores = new ArrayList<>();
    }

    public List<Expresion> getExpresiones() {
        return expresiones;
    }

    public void setExpresiones(List<Expresion> expresiones) {
        this.expresiones = expresiones;
    }

    public List<Lexema> getOperadores() {
        return operadores;
    }

    public void setOperadores(List<Lexema> operadores) {
        this.operadores = operadores;
    }

    public List<Lexema> getIdentificadores() {
        return identificadores;
    }

    public void setIdentificadores(List<Lexema> identificadores) {
        this.identificadores = identificadores;
    }

    @Override
    public List<co.edu.eam.tlf.analizadorsintactico.sentencias.definicion.Sentencia> llenarHijos() {
        hijos = new ArrayList<>();
        for (int i = 0; i < expresiones.size(); i++) {
            hijos.add(expresiones.get(i));
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
