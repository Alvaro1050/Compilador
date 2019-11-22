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
    private Lexema nuevo;
    private Lexema tipoEspecificador;
    private Lista<Argumento> listaArgumentos;
    private Lexema parentesisA1;
    private Lexema parentesisC1;
    private Lexema parentesisA2;
    private Lexema parentesisC2;
    private Lexema parentesisA3;
    private Lexema parentesisC3;

    public CrearExpresion() {
        listaArgumentos = new Lista<>();
    }

    public Expresion getExpresion() {
        return expresion;
    }

    public Lexema getNuevo() {
        return nuevo;
    }

    public void setNuevo(Lexema nuevo) {
        this.nuevo = nuevo;
    }

    public void setExpresion(Expresion expresion) {
        this.expresion = expresion;
    }

    public Lexema getIdentificador() {
        return identificador;
    }

    public Lexema getParentesisA1() {
        return parentesisA1;
    }

    public void setParentesisA1(Lexema parentesisA1) {
        this.parentesisA1 = parentesisA1;
    }

    public Lexema getParentesisC1() {
        return parentesisC1;
    }

    public void setParentesisC1(Lexema parentesisC1) {
        this.parentesisC1 = parentesisC1;
    }

    public Lexema getParentesisA2() {
        return parentesisA2;
    }

    public void setParentesisA2(Lexema parentesisA2) {
        this.parentesisA2 = parentesisA2;
    }

    public Lexema getParentesisC2() {
        return parentesisC2;
    }

    public void setParentesisC2(Lexema parentesisC2) {
        this.parentesisC2 = parentesisC2;
    }

    public Lexema getParentesisA3() {
        return parentesisA3;
    }

    public void setParentesisA3(Lexema parentesisA3) {
        this.parentesisA3 = parentesisA3;
    }

    public Lexema getParentesisC3() {
        return parentesisC3;
    }

    public void setParentesisC3(Lexema parentesisC3) {
        this.parentesisC3 = parentesisC3;
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

        str.append("new ");

        if (identificador != null) {
            str.append(identificador.getToken());

            str.append(parentesisA1.getToken());

            for (Sentencia sentencia : listaArgumentos.getSentencias()) {
                str.append(sentencia.parse());
            }

            str.append(parentesisC1.getToken());

            return str.toString();
        }

        if (tipoEspecificador != null) {
            str.append(tipoEspecificador.getToken());

            if (parentesisA2 != null) {
                str.append(parentesisA3.getToken());

                str.append(expresion.parse());
                str.append(parentesisC3.getToken());
                return str.toString();

            }

        }

        if (expresion != null) {
            str.append(parentesisA2.getToken());
            str.append(expresion.parse());
            str.append(parentesisC2.getToken());
        }

        return str.toString();
    }
}
