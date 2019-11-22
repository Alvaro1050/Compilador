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
public class Mensaje extends co.edu.eam.tlf.analizadorsintactico.sentencias.definicion.Sentencia {

    private Lexema mensaje;
    private Lexema comillaAbierta;
    private Lexema comillaCerrada;
    private Expresion expresion;

    public Mensaje() {
    }

    public Mensaje(Lexema mensaje, Lexema comillaAbierta, Lexema comillaCerrada, Expresion expresion) {
        this.mensaje = mensaje;
        this.comillaAbierta = comillaAbierta;
        this.comillaCerrada = comillaCerrada;
        this.expresion = expresion;
    }

    public Lexema getMensaje() {
        return mensaje;
    }

    public void setMensaje(Lexema mensaje) {
        this.mensaje = mensaje;
    }

    public Lexema getComillaAbierta() {
        return comillaAbierta;
    }

    public void setComillaAbierta(Lexema comillaAbierta) {
        this.comillaAbierta = comillaAbierta;
    }

    public Lexema getComillaCerrada() {
        return comillaCerrada;
    }

    public void setComillaCerrada(Lexema comillaCerrada) {
        this.comillaCerrada = comillaCerrada;
    }

    public Expresion getExpresion() {
        return expresion;
    }

    public void setExpresion(Expresion expresion) {
        this.expresion = expresion;
    }

    @Override
    public List<Sentencia> llenarHijos() {
        hijos = new ArrayList<>();

        hijos.add(new SentenciaToken(mensaje));
        hijos.add(new SentenciaToken(comillaAbierta));
        hijos.add(new SentenciaToken(comillaCerrada));
        hijos.add(expresion);

        return hijos;
    }

    @Override
    public String toString() {
        return "Mensaje";
    }

    @Override
    public String parse() {
        StringBuilder str = new StringBuilder();
        str.append(mensaje.getToken());
        str.append("( ");
        if (comillaAbierta != null) {

            str.append(comillaAbierta.getToken());
        }

        if (expresion != null) {
            str.append(expresion.parse());
        }
        
        if (comillaCerrada != null) {
            str.append(comillaCerrada.getToken());
        }
         str.append(" )");
          str.append(";");

        return str.toString();
    }

}
