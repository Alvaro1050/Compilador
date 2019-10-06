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

        GramaticaExpresion gramaticaExpresion = new GramaticaExpresion();
        boolean continuar = true;
        do {

            Expresion expresion = gramaticaExpresion.analizar(expresionLogica, flujoTokens);

            if (expresion != null) {
                expresionLogica.getExpresiones().add(expresion);
                lexema = flujoTokens.getTokenActual();
                if (lexema.getToken().equals("&") || lexema.getToken().equals("|")) {
                    expresionLogica.getOperadores().add(lexema);
                    lexema = flujoTokens.avanzar();

                    if (lexema == null) {
                        throw new SintacticException(new Lexema("", ""), "expresion");
                    }

                    if (lexema.getToken().equals("!")) {
                        lexema = flujoTokens.avanzar();
                    }

                    continue;
                }
            }

            if (lexema.getTipoLexema().equals("Identificador")) {
                expresionLogica.getIdentificadores().add(lexema);
                lexema = flujoTokens.avanzar();
                if (lexema.getToken().equals("&") || lexema.getToken().equals("|")) {
                    expresionLogica.getOperadores().add(lexema);
                    lexema = flujoTokens.avanzar();

                    if (lexema == null) {
                        throw new SintacticException(new Lexema("", ""), "expresion");
                    }

                    if (lexema.getToken().equals("!")) {
                        lexema = flujoTokens.avanzar();
                    }

                    continue;
                }
            }

            continuar = false;
        } while (continuar);

        if (!expresionLogica.getExpresiones().isEmpty() || !expresionLogica.getIdentificadores().isEmpty()) {
            return expresionLogica;
        } else {
            return null;
        }

    }

}
