/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eam.tlf.analizadorsintactico.gramatica.implementaciones;

import co.edu.eam.tlf.analizadorlexico.modelo.Lexema;
import co.edu.eam.tlf.analizadorsintactico.gramatica.definiciones.Gramatica;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.CrearExpresion;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Expresion;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.ExpresionCadena;
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
        GramaticaExpresionCadena gramaticaExpresionCadena = new GramaticaExpresionCadena();
        GramaticaCrearExpresion gramaticaCrearExpresion = new GramaticaCrearExpresion();

        boolean continuar = true;
        do {
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
            /*
             ExpresionCadena expresionCadena = gramaticaExpresionCadena.analizar(expresion, flujoTokens);

             if (expresionCadena != null) {
             expresion.setExpresionCadena(expresionCadena);
             continue;
             }
             */

            continuar = false;
        } while (continuar);

        lexema = flujoTokens.getTokenActual();

        if (expresion.getExpresionNumerica() == null && expresion.getExpresionTest() == null && expresion.getExpresionCadena() == null) {
            return null;
        } else {
            return expresion;
        }

    }
}
