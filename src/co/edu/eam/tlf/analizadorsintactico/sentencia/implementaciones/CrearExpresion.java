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
public class CrearExpresion extends Sentencia {

    private Expresion expresion;
    private Expresion expresion2;
    private Lexema identificador;
    private Lexema tipoEspecificador;
    private Lista<Argumento> listaArgumentos;

    public CrearExpresion() {
        listaArgumentos = new Lista<>();
    }

    public Expresion getExpresion2() {
        return expresion2;
    }

    public void setExpresion2(Expresion expresion2) {
        this.expresion2 = expresion2;
    }

    public Expresion getExpresion() {
        return expresion;
    }

    public void setExpresion(Expresion expresion) {
        this.expresion = expresion;
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

    public Lista<Argumento> getListaArgumentos() {
        return listaArgumentos;
    }

    public void setListaArgumentos(Lista<Argumento> listaArgumentos) {
        this.listaArgumentos = listaArgumentos;
    }

    @Override
    public List<co.edu.eam.tlf.analizadorsintactico.sentencias.definicion.Sentencia> llenarHijos() {
        hijos = new ArrayList<>();
        hijos.add(expresion);

        hijos.add(new SentenciaToken(identificador));
        hijos.add(new SentenciaToken(tipoEspecificador));

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
