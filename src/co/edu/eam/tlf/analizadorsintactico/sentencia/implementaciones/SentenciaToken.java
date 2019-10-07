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
 * @author alvar
 */
public class SentenciaToken extends Sentencia {

    private Lexema simbolo;

    public SentenciaToken() {
    }

    public SentenciaToken(Lexema simbolo) {
        this.simbolo = simbolo;
    }

    @Override
    public List<Sentencia> llenarHijos() {
        hijos = new ArrayList<>();
        hijos.add(new SentenciaToken(simbolo));
        return hijos;

    }

    @Override
    public String toString() {

        return simbolo.getTipoLexema() + ":" + simbolo.getToken();

    }

    public Lexema getSimbolo() {
        return simbolo;
    }

    @Override
    public String parse() {
        // TODO Auto-generated method stub
        return null;
    }

}
