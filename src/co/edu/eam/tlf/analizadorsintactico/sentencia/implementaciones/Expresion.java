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
    private ExpresionCadena expresionCadena;
    private CrearExpresion crearExpresion;
    private Lexema identificador;
    private Lista<Parametro> listaArgumentos;
    private List<Expresion> expresiones;
    private List<Expresion2> expresion2s;

    public Expresion() {
        listaArgumentos = new Lista<>();
        expresiones = new ArrayList<>();
        expresion2s = new ArrayList<>();
    }

    public Lexema getIdentificador() {
        return identificador;
    }

    public List<Expresion2> getExpresion2s() {
        return expresion2s;
    }

    public void setExpresion2s(List<Expresion2> expresion2s) {
        this.expresion2s = expresion2s;
    }

    public void setIdentificador(Lexema identificador) {
        this.identificador = identificador;
    }

    public List<Expresion> getExpresiones() {
        return expresiones;
    }

    public void setExpresiones(List<Expresion> expresiones) {
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

    public ExpresionCadena getExpresionCadena() {
        return expresionCadena;
    }

    public void setExpresionCadena(ExpresionCadena expresionCadena) {
        this.expresionCadena = expresionCadena;
    }

    public CrearExpresion getCrearExpresion() {
        return crearExpresion;
    }

    public void setCrearExpresion(CrearExpresion crearExpresion) {
        this.crearExpresion = crearExpresion;
    }

    public Lista<Parametro> getListaArgumentos() {
        return listaArgumentos;
    }

    public void setListaArgumentos(Lista<Parametro> listaArgumentos) {
        this.listaArgumentos = listaArgumentos;
    }

    @Override
    public List<Sentencia> llenarHijos() {

        hijos = new ArrayList<>();

        hijos.add(new SentenciaToken(identificador));

        if (!listaArgumentos.getSentencias().isEmpty()) {
            hijos.add(listaArgumentos);
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
