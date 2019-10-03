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
 * @author Lenovo
 */
public class InicializarVariable extends Sentencia {

    private Lexema variable;
    private Expresion expresion;
    private Lexema valor;

    public InicializarVariable() {
    }

    public Lexema getVariable() {
        return variable;
    }

    public void setVariable(Lexema variable) {
        this.variable = variable;
    }

    public Expresion getExpresion() {
        return expresion;
    }

    public void setExpresion(Expresion expresion) {
        this.expresion = expresion;
    }

    public Lexema getValor() {
        return valor;
    }

    public void setValor(Lexema valor) {
        this.valor = valor;
    }

    @Override
    public List<Sentencia> llenarHijos() {
        hijos = new ArrayList<>();

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
