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
public class ExpresionTest extends Sentencia {

    private Lexema expresion1;
    private Lexema operador;
    private Lexema expresion2;

    public ExpresionTest() {

    }

    public Lexema getExpresion1() {
        return expresion1;
    }

    public void setExpresion1(Lexema expresion1) {
        this.expresion1 = expresion1;
    }

    public Lexema getOperador() {
        return operador;
    }

    public void setOperador(Lexema operador) {
        this.operador = operador;
    }

    public Lexema getExpresion2() {
        return expresion2;
    }

    public void setExpresion2(Lexema expresion2) {
        this.expresion2 = expresion2;
    }

    @Override
    public List<co.edu.eam.tlf.analizadorsintactico.sentencias.definicion.Sentencia> llenarHijos() {

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
