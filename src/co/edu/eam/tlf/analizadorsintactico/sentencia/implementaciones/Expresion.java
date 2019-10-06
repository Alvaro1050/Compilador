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
public class Expresion extends Sentencia {

    private ExpresionNumerica expresionNumerica;
    private ExpresionTest expresionTest;
    private CrearExpresion crearExpresion;
    private Lexema identificador;
    private Lista<Expresion> expresiones;
    private Lista<Expresion2> expresion2s;

    public Expresion() {
        expresiones = new Lista<>();
        expresion2s = new Lista<>();
    }

    public Lexema getIdentificador() {
        return identificador;
    }

    public Lista<Expresion2> getExpresion2s() {
        return expresion2s;
    }

    public void setExpresion2s(Lista<Expresion2> expresion2s) {
        this.expresion2s = expresion2s;
    }

    public void setIdentificador(Lexema identificador) {
        this.identificador = identificador;
    }

    public Lista<Expresion> getExpresiones() {
        return expresiones;
    }

    public void setExpresiones(Lista<Expresion> expresiones) {
        this.expresiones = expresiones;
    }

    public ExpresionNumerica getExpresionNumerica() {
        return expresionNumerica;
    }

    public void setExpresionNumerica(ExpresionNumerica expresionNumerica) {
        this.expresionNumerica = expresionNumerica;
    }

    public ExpresionTest getExpresionTest() {
        return expresionTest;
    }

    public void setExpresionTest(ExpresionTest expresionTest) {
        this.expresionTest = expresionTest;
    }

    public CrearExpresion getCrearExpresion() {
        return crearExpresion;
    }

    public void setCrearExpresion(CrearExpresion crearExpresion) {
        this.crearExpresion = crearExpresion;
    }

    @Override
    public List<Sentencia> llenarHijos() {

        hijos = new ArrayList<>();
        hijos.add(expresionNumerica);
        hijos.add(expresionTest);
        hijos.add(crearExpresion);
        hijos.add(new SentenciaToken(identificador));
        if (!expresiones.getSentencias().isEmpty()) {
            hijos.add(expresiones);
        }
        if (!expresion2s.getSentencias().isEmpty()) {
            hijos.add(expresion2s);
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

        str.append("Expresion numerica");

        str.append(expresionNumerica);
        
        str.append("Expresion Test");

        str.append(expresionTest);
        
        str.append("Crear Expresion");

        str.append(crearExpresion);
        
        str.append("Identificador Expresion");

        str.append(identificador.getToken());

        for (Sentencia sentencia : expresiones.getSentencias()) {
            str.append("Expresiones: ");
            str.append(sentencia.parse());
        }

        for (Sentencia sentencia : expresion2s.getSentencias()) {
            str.append("Expresiones 2: ");
            str.append(sentencia);
        }

        return str.toString();
    }

}
