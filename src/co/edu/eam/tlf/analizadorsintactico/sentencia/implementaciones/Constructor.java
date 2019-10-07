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
public class Constructor extends Sentencia {

    Lexema nombre;

    Lista<Parametro> listaParametros;

    Lista<Sentencia> listaSentencia;

    public Constructor(Lexema nombre, Lista<Parametro> listaParametros, Lista<Sentencia> listaSentencia) {
        this.nombre = nombre;
        this.listaParametros = listaParametros;
        this.listaSentencia = listaSentencia;
    }

    public Constructor() {
        listaParametros = new Lista<>();
        listaSentencia = new Lista<>();
    }

    public Lexema getNombre() {
        return nombre;
    }

    public void setNombre(Lexema nombre) {
        this.nombre = nombre;
    }

    public Lista<Parametro> getListaParametros() {
        return listaParametros;
    }

    public void setListaParametros(Lista<Parametro> listaParametros) {
        this.listaParametros = listaParametros;
    }

    public Lista<Sentencia> getListaSentencia() {
        return listaSentencia;
    }

    public void setListaSentencia(Lista<Sentencia> listaSentencia) {
        this.listaSentencia = listaSentencia;
    }

    @Override
    public List<Sentencia> llenarHijos() {

        hijos = new ArrayList<>();

        if (nombre != null) {

            hijos.add(new SentenciaToken(nombre));
        }
        if (!listaParametros.getSentencias().isEmpty()) {
            hijos.add(listaParametros);
        }
        if (!listaSentencia.getSentencias().isEmpty()) {
            hijos.add(listaSentencia);
        }
        return hijos;

    }

    @Override
    public String toString() {

        return "Constructor:" + nombre.getToken();

    }

    @Override
    public String parse() {

        StringBuilder str = new StringBuilder();

        str.append("clase");

        str.append(nombre.getToken());
        str.append("[");

        for (Sentencia sentencia : listaParametros.getSentencias()) {
            str.append(sentencia.parse());
        }

        for (Sentencia sentencia : listaSentencia.getSentencias()) {
            str.append(sentencia.parse());
        }

        str.append("]");

        return str.toString();
    }

}
