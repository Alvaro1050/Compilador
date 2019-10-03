/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eam.tlf.analizadorsintactico.gramatica.implementaciones;

import co.edu.eam.tlf.analizadorlexico.modelo.Lexema;
import co.edu.eam.tlf.analizadorsintactico.excepciones.SintacticException;
import co.edu.eam.tlf.analizadorsintactico.gramatica.definiciones.Gramatica;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.ExpresionNumerica;
import co.edu.eam.tlf.analizadorsintactico.sentencias.definicion.Sentencia;

/**
 *
 * @author Lenovo
 */
public class GramaticaExpresionNumerica implements Gramatica {

    @Override
    public ExpresionNumerica analizar(Sentencia padre, FlujoTokens flujoTokens) {

        ExpresionNumerica expresionNumerica = new ExpresionNumerica();

        flujoTokens.guardarPosicion();

        Lexema lexema = flujoTokens.getTokenActual();

        if (lexema.getToken().equals("-")) {

            lexema = flujoTokens.avanzar();

            if (lexema == null) {
                throw new SintacticException(new Lexema("", ""), "expresion");

            }

            if (lexema.getTipoLexema().equals("Identificador")) {
                expresionNumerica.setExpresion1(lexema);
                lexema = flujoTokens.avanzar();

                return expresionNumerica;
            } else {
                throw new SintacticException(lexema, "Identificador");

            }

        }

        if (lexema.getTipoLexema().equals("Identificador")) {
            expresionNumerica.setExpresion1(lexema);
            lexema = flujoTokens.avanzar();

            if (lexema == null) {
                throw new SintacticException(new Lexema("", ""), "expresion");
            }

            if (lexema.getToken().equals("++") || lexema.getToken().equals("--")) {
                expresionNumerica.setOperador(lexema);
                lexema = flujoTokens.avanzar();
                return expresionNumerica;
            }

            if (lexema.getToken().equals("+") || lexema.getToken().equals("+=")
                    || lexema.getToken().equals("-") || lexema.getToken().equals("-=")
                    || lexema.getToken().equals("=") || lexema.getToken().equals("/")
                    || lexema.getToken().equals("/=") || lexema.getToken().equals("%")
                    || lexema.getToken().equals("%=")) {
                expresionNumerica.setOperador(lexema);
                lexema = flujoTokens.avanzar();

                if (lexema == null) {
                    throw new SintacticException(new Lexema("", ""), "expresion");
                }

                if (lexema.getTipoLexema().equals("Identificador")) {
                    expresionNumerica.setExpresion2(lexema);
                    lexema = flujoTokens.avanzar();

                    return expresionNumerica;
                }

            } else {
                flujoTokens.backTrack();
                return null;
            }
        } else {
            flujoTokens.backTrack();
            return null;
        }

        return null;
    }

}
