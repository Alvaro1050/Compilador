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
    private ExpresionLogica expresionLogica;
    private ExpresionCadena expresionCadena;
    private CastExpresion castExpresion;
    private CrearExpresion crearExpresion;
    private Lexema tipo;
    private Lista<Parametro> listaArgumentos;

    public Expresion() {
        listaArgumentos = new Lista<>();

    }

    public Expresion(ExpresionNumerica expresionNumerica, ExpresionTest expresionTest, ExpresionLogica expresionLogica, ExpresionCadena expresionCadena, CastExpresion castExpresion, CrearExpresion crearExpresion, Lexema tipo, Lista<Parametro> listaArgumentos) {
        this.expresionNumerica = expresionNumerica;
        this.expresionTest = expresionTest;
        this.expresionLogica = expresionLogica;
        this.expresionCadena = expresionCadena;
        this.castExpresion = castExpresion;
        this.crearExpresion = crearExpresion;
        this.tipo = tipo;
        this.listaArgumentos = listaArgumentos;
    }

    public Lexema getTipo() {
        return tipo;
    }

    public void setTipo(Lexema tipo) {
        this.tipo = tipo;
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

    public ExpresionLogica getExpresionLogica() {
        return expresionLogica;
    }

    public void setExpresionLogica(ExpresionLogica expresionLogica) {
        this.expresionLogica = expresionLogica;
    }

    public ExpresionCadena getExpresionCadena() {
        return expresionCadena;
    }

    public void setExpresionCadena(ExpresionCadena expresionCadena) {
        this.expresionCadena = expresionCadena;
    }

    public CastExpresion getCastExpresion() {
        return castExpresion;
    }

    public void setCastExpresion(CastExpresion castExpresion) {
        this.castExpresion = castExpresion;
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

        hijos.add(new SentenciaToken(tipo));

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
