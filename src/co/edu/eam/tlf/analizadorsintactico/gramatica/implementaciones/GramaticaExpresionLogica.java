/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eam.tlf.analizadorsintactico.gramatica.implementaciones;

import co.edu.eam.tlf.analizadorlexico.modelo.Lexema;
import co.edu.eam.tlf.analizadorsintactico.excepciones.SintacticException;
import co.edu.eam.tlf.analizadorsintactico.gramatica.definiciones.Gramatica;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Expresion;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.ExpresionLogica;
import co.edu.eam.tlf.analizadorsintactico.sentencias.definicion.Sentencia;

/**
 *
 * @author Lenovo
 */
public class GramaticaExpresionLogica implements Gramatica {

    @Override
    public ExpresionLogica analizar(Sentencia padre, FlujoTokens flujoTokens) {

        ExpresionLogica expresionLogica = new ExpresionLogica();

        flujoTokens.guardarPosicion();

        Lexema lexema = flujoTokens.getTokenActual();

        if (lexema.getToken().equals("!")) {

            expresionLogica.setOperador(lexema);
            lexema = flujoTokens.avanzar();

            GramaticaExpresion gramaticaExpresion = new GramaticaExpresion();

            Expresion expresion = gramaticaExpresion.analizar(expresionLogica, flujoTokens);
            if (expresion != null) {
                expresionLogica.setExpresion(expresion);
                return expresionLogica;
            }

        }

        GramaticaExpresion gramaticaExpresion = new GramaticaExpresion();

        Expresion expresion = gramaticaExpresion.analizar(expresionLogica, flujoTokens);
        if (expresion != null) {
            expresionLogica.setExpresion(expresion);

            if (lexema.getToken().equals("&") || lexema.getToken().equals("|")) {
                expresionLogica.setOperador(lexema);
                lexema = flujoTokens.avanzar();

                if (lexema == null) {
                    throw new SintacticException(new Lexema("", ""), "expresion");
                }

                Expresion expresion1 = gramaticaExpresion.analizar(expresionLogica, flujoTokens);
                if (expresion != null) {
                    expresionLogica.setExpresion2(expresion1);
                    return expresionLogica;
                } else {
                    throw new SintacticException(lexema, "expresion");

                }

            } else {
                throw new SintacticException(lexema, "operador");

            }
        }

        if (lexema.getTipoLexema().equals("Identificador")) {
            expresionLogica.setIdentificador(lexema);
            return expresionLogica;
        }
        return null;

    }

}
