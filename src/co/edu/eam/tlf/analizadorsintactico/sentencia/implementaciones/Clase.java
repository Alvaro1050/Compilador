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
public class Clase extends Sentencia {

    /**
     * Lista de metodos de la clase....
     */
    private Lista<Metodo> listaMetodos;

    /**
     * Lista de atributos de la clase.
     */
    private Lista<Atributo> listaAtributos;

    private Lista<Sentencia> listaSentencia;
    /**
     * Nombre de la clase.
     */
    private Lexema nombreClase;

    public Clase() {
        listaAtributos = new Lista();
        listaMetodos = new Lista<>();
        listaSentencia = new Lista<>();
    }

    @Override
    public List<Sentencia> llenarHijos() {

        hijos = new ArrayList<>();

        hijos.add(new SentenciaToken(nombreClase));
        if (!listaAtributos.getSentencias().isEmpty()) {
            hijos.add(listaAtributos);
        }
        if (!listaMetodos.getSentencias().isEmpty()) {
            hijos.add(listaMetodos);
        }
        return hijos;
    }

    @Override
    public String toString() {
        return "Clase:" + nombreClase.getToken();
    }

    public Lista<Metodo> getListaMetodos() {
        return listaMetodos;
    }

    public void setListaMetodos(Lista<Metodo> listaMetodos) {
        this.listaMetodos = listaMetodos;
    }

    public Lista<Atributo> getListaAtributos() {
        return listaAtributos;
    }

    public void setListaAtributos(Lista<Atributo> listaAtributos) {
        this.listaAtributos = listaAtributos;
    }

    public Lista<Sentencia> getListaSentencia() {
        return listaSentencia;
    }

    public void setListaSentencia(Lista<Sentencia> listaSentencia) {
        this.listaSentencia = listaSentencia;
    }

    public Lexema getNombreClase() {
        return nombreClase;
    }

    public void setNombreClase(Lexema nombreClase) {
        this.nombreClase = nombreClase;
    }

    @Override
    public String parse() {

        StringBuilder str = new StringBuilder();

        str.append("class ");

        str.append(nombreClase.getToken());
        str.append(" { \n");

        for (Sentencia sentencia : listaAtributos.getSentencias()) {
            str.append(sentencia.parse());
        }

        for (Sentencia sentencia : listaMetodos.getSentencias()) {
            str.append(sentencia.parse());
        }

        str.append("}");

        return str.toString();
    }

}
