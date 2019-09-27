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
class DeclaradorVariable extends Sentencia {

    private Lexema tipo;
    private Lexema identificador;
    Lista<DeclaradorVariable> listaDeclaradorVariable;

    public DeclaradorVariable(Lexema tipo, Lexema identificador, Lista<DeclaradorVariable> listaDeclaradorVariable) {
        this.tipo = tipo;
        this.identificador = identificador;
        this.listaDeclaradorVariable = listaDeclaradorVariable;
    }

    public DeclaradorVariable() {
        listaDeclaradorVariable = new Lista<>();
    }

    public Lexema getTipo() {
        return tipo;
    }

    public void setTipo(Lexema tipo) {
        this.tipo = tipo;
    }

    public Lexema getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Lexema identificador) {
        this.identificador = identificador;
    }

    public Lista<DeclaradorVariable> getListaDeclaradorVariable() {
        return listaDeclaradorVariable;
    }

    public void setListaDeclaradorVariable(Lista<DeclaradorVariable> listaDeclaradorVariable) {
        this.listaDeclaradorVariable = listaDeclaradorVariable;
    }

    @Override
    public List<Sentencia> llenarHijos() {
        hijos = new ArrayList<>();

        hijos.add(new SentenciaToken(tipo));
        hijos.add(new SentenciaToken(identificador));

        if (!listaDeclaradorVariable.getSentencias().isEmpty()) {
            hijos.add(listaDeclaradorVariable);
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
