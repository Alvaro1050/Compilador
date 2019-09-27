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
class CrearExpresion extends Sentencia {

    private Expresion expresion;
    private Lexema neu;
    private Lexema identificador;
    private Lexema tipoEspecificador;
    private Lista<Parametro> listaParametros;

    public CrearExpresion(Expresion expresion, Lexema neu, Lexema identificador, Lexema tipoEspecificador, Lista<Parametro> listaParametros) {
        this.expresion = expresion;
        this.neu = neu;
        this.identificador = identificador;
        this.tipoEspecificador = tipoEspecificador;
        this.listaParametros = listaParametros;
    }

    public CrearExpresion() {
        listaParametros = new Lista<>();
    }

    public Expresion getExpresion() {
        return expresion;
    }

    public void setExpresion(Expresion expresion) {
        this.expresion = expresion;
    }

    public Lexema getNeu() {
        return neu;
    }

    public void setNeu(Lexema neu) {
        this.neu = neu;
    }

    public Lexema getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Lexema identificador) {
        this.identificador = identificador;
    }

    public Lexema getTipoEspecificador() {
        return tipoEspecificador;
    }

    public void setTipoEspecificador(Lexema tipoEspecificador) {
        this.tipoEspecificador = tipoEspecificador;
    }

    public Lista<Parametro> getListaParametros() {
        return listaParametros;
    }

    public void setListaParametros(Lista<Parametro> listaParametros) {
        this.listaParametros = listaParametros;
    }

    @Override
    public List<Sentencia> llenarHijos() {
        hijos = new ArrayList<>();
        hijos.add(expresion);

        hijos.add(new SentenciaToken(neu));
        hijos.add(new SentenciaToken(identificador));
        hijos.add(new SentenciaToken(tipoEspecificador));

        if (!listaParametros.getSentencias().isEmpty()) {
            hijos.add(listaParametros);
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
