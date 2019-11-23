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
 * @author caferrerb
 */
public class Metodo extends Sentencia {

    /**
     * Nombre del metodo
     */
    private Lexema nombre;

    /**
     * Tipo de retorno.
     */
    private Lexema retorno;

    /**
     * Lista de parametros.
     */
    private Lista<Parametro> listaParametros;

    /**
     * Instrucciones dentro del metodo.
     */
    private Lista<Sentencia> listaSentencias;

    /**
     * COnstructor
     */
    public Metodo() {
        listaParametros = new Lista<>();
        listaSentencias = new Lista<>();
    }

    @Override
    public List<Sentencia> llenarHijos() {

        hijos = new ArrayList<>();

        hijos.add(new SentenciaToken(retorno));
        hijos.add(new SentenciaToken(nombre));

        if (!listaParametros.getSentencias().isEmpty()) {
            hijos.add(listaParametros);
        }
        if (!listaSentencias.getSentencias().isEmpty()) {
            hijos.add(listaSentencias);
        }
        return hijos;

    }

    @Override
    public String toString() {
        return "Metodo:  " + nombre.getToken();
    }

    public Lexema getNombre() {
        return nombre;
    }

    public void setNombre(Lexema nombre) {
        this.nombre = nombre;
    }

    public Lexema getRetorno() {
        return retorno;
    }

    public void setRetorno(Lexema retorno) {
        this.retorno = retorno;
    }

    public Lista<Parametro> getListaParametros() {
        return listaParametros;
    }

    public void setListaParametros(Lista<Parametro> listaParametros) {
        this.listaParametros = listaParametros;
    }

    public Lista<Sentencia> getListaSentencias() {
        return listaSentencias;
    }

    public void setListaSentencias(Lista<Sentencia> listaSentencias) {
        this.listaSentencias = listaSentencias;
    }

    @Override
    public String parse() {
        StringBuilder str = new StringBuilder();
        str.append(" \n");

        if (retorno.getToken().equals("cadena")) {
            str.append("String").append(" ");

        } else if (retorno.getToken().equals("entero")) {
            str.append("int").append(" ");
        } else if (retorno.getToken().equals("boo")) {
            str.append("boolean").append(" ");
        } else if (retorno.getToken().equals("decimal")) {
            str.append("double").append(" ");
        } else if (retorno.getToken().equals("flotante")) {
            str.append("float").append(" ");
        }
        str.append(nombre.getToken());
        str.append("( ");
        for (int i = 0; i < listaParametros.getSentencias().size(); i++) {
            str.append(listaParametros.getSentencias().get(i).parse());
            if (i == listaParametros.getSentencias().size() - 1) {

            } else {
                str.append(", ");
            }
        }
        str.append("){ \n");

        for (Sentencia sentencia : listaSentencias.getSentencias()) {
            str.append(sentencia.parse());
        }
        str.append("} \n");
        return str.toString();
    }

}
