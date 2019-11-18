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
    private Lexema identificador;
    private Lexema tipoEspecificador;
    private Lista<Argumento> listaArgumentos;

    public CrearExpresion() {
        listaArgumentos = new Lista<>();
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

        if (expresion != null) {

            hijos.add(expresion);
        }

        if (identificador != null) {
            hijos.add(new SentenciaToken(identificador));
        }

        if (tipoEspecificador != null) {
            hijos.add(new SentenciaToken(tipoEspecificador));
        }

        if (!listaArgumentos.getSentencias().isEmpty()) {
            hijos.add(listaArgumentos);
        }

        return hijos;

    }

    @Override
    public String toString() {
        return "Crear expresion:";
    }

    @Override
    public String parse() {

        StringBuilder str = new StringBuilder();
        if (identificador != null) {
            str.append(identificador.getToken());

        }
        if (tipoEspecificador != null) {
            str.append(tipoEspecificador.getToken());

        }
        if (expresion != null) {
            str.append(expresion.parse());

        }

        for (Sentencia sentencia : listaArgumentos.getSentencias()) {
            str.append(sentencia.parse());
        }

        return str.toString();
    }
}
