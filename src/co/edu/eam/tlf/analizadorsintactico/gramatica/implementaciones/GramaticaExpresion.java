/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eam.tlf.analizadorsintactico.gramatica.implementaciones;

import co.edu.eam.tlf.analizadorlexico.modelo.Lexema;
import co.edu.eam.tlf.analizadorsintactico.excepciones.SintacticException;
import co.edu.eam.tlf.analizadorsintactico.gramatica.definiciones.Gramatica;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.CrearExpresion;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Expresion;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Expresion2;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.ExpresionNumerica;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.ExpresionTest;
import co.edu.eam.tlf.analizadorsintactico.sentencias.definicion.Sentencia;

/**
 *
 * @author Lenovo
 */
public class GramaticaExpresion implements Gramatica {

    @Override
    public Expresion analizar(Sentencia padre, FlujoTokens flujoTokens) {

        Expresion expresion = new Expresion();

        flujoTokens.guardarPosicion();

        Lexema lexema = flujoTokens.getTokenActual();

        GramaticaExpresionNumerica gramaticaExpresionNumerica = new GramaticaExpresionNumerica();
        GramaticaExpresionTest gramaticaExpresionTest = new GramaticaExpresionTest();
        GramaticaExpresion2 gramaticaExpresion2 = new GramaticaExpresion2();
        GramaticaCrearExpresion gramaticaCrearExpresion = new GramaticaCrearExpresion();
        GramaticaExpresion gramaticaExpresion = new GramaticaExpresion();

        boolean continuar = true;
        do {
            lexema = flujoTokens.getTokenActual();
            ExpresionNumerica expresionNumerica = gramaticaExpresionNumerica.analizar(expresion, flujoTokens);

            if (expresionNumerica != null) {
                expresion.setExpresionNumerica(expresionNumerica);
                continue;
            }
            ExpresionTest expresionTest = gramaticaExpresionTest.analizar(expresion, flujoTokens);

            if (expresionTest != null) {
                expresion.setExpresionTest(expresionTest);
                continue;
            }

            CrearExpresion crearExpresion = gramaticaCrearExpresion.analizar(expresion, flujoTokens);

            if (crearExpresion != null) {
                expresion.setCrearExpresion(crearExpresion);
                continue;
            }

            Expresion2 expresiona = gramaticaExpresion2.analizar(expresion, flujoTokens);

            if (expresiona != null) {
                expresion.getExpresion2s().add(expresiona);
                lexema = flujoTokens.getTokenActual();
                continue;
            }

            lexema = flujoTokens.getTokenActual();
            if (lexema.getTipoLexema().equals("Identificador")) {
                expresion.setIdentificador(lexema);
                lexema = flujoTokens.avanzar();
                continue;
            }

            if (lexema.getToken().equals("vacio")) {
                expresion.setIdentificador(lexema);
                lexema = flujoTokens.avanzar();
                continue;
            }

            if (lexema.getToken().equals("(")) {
                lexema = flujoTokens.avanzar();

                if (lexema == null) {
                    throw new SintacticException(new Lexema("", ""), "expresion");
                }

                Expresion expresion1 = gramaticaExpresion.analizar(expresion, flujoTokens);

                if (expresion1 != null) {
                    expresion.getExpresiones().add(expresion);
                    lexema = flujoTokens.getTokenActual();
                    if (lexema.getToken().equals(")")) {
                        lexema = flujoTokens.avanzar();
                        continue;
                    } else {
                        throw new SintacticException(lexema, ")");
                    }
                } else {
                    throw new SintacticException(lexema, "expresion");
                }
            }

            continuar = false;
        } while (continuar);

        lexema = flujoTokens.getTokenActual();

        if (expresion.getIdentificador() == null && expresion.getExpresionNumerica() == null && expresion.getExpresionTest() == null && expresion.getCrearExpresion() == null) {
            return null;
        } else {
            return expresion;
        }

    }
}
