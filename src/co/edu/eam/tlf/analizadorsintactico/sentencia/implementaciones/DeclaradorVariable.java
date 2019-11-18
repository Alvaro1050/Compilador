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
public class DeclaradorVariable extends co.edu.eam.tlf.analizadorsintactico.sentencias.definicion.Sentencia {

    private Lexema tipo;
    private Lexema identificador;
    private Lista<DeclaradorVariable> listaDeclaradorVariable;

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
    public List<co.edu.eam.tlf.analizadorsintactico.sentencias.definicion.Sentencia> llenarHijos() {
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
        return "Declarar variable: " + identificador.getToken();
    }

    @Override
    public String parse() {

        StringBuilder str = new StringBuilder();
        if (identificador != null) {
            str.append(identificador.getToken());

        }
        if (tipo != null) {
            str.append(tipo.getToken());

        }

        for (Sentencia sentencia : listaDeclaradorVariable.getSentencias()) {
            str.append("Argumentos: ");
            str.append(sentencia.parse());
        }

        return str.toString();
    }

}
