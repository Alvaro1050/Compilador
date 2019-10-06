/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eam.tlf.analizadorsintactico.gramatica.implementaciones;

import co.edu.eam.tlf.analizadorlexico.modelo.Lexema;
import co.edu.eam.tlf.analizadorsintactico.excepciones.SintacticException;
import co.edu.eam.tlf.analizadorsintactico.gramatica.definiciones.Gramatica;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.ExpresionTest;
import co.edu.eam.tlf.analizadorsintactico.sentencias.definicion.Sentencia;

/**
 *
 * @author Lenovo
 */
public class GramaticaExpresionTest implements Gramatica {

    @Override
    public ExpresionTest analizar(Sentencia padre, FlujoTokens flujoTokens) {

        ExpresionTest expresionTest = new ExpresionTest();

        flujoTokens.guardarPosicion();

        Lexema lexema = flujoTokens.getTokenActual();

        if (lexema.getTipoLexema().equals("Identificador")) {
            expresionTest.setExpresion1(lexema);
            lexema = flujoTokens.avanzar();

            if (lexema == null) {
                throw new SintacticException(new Lexema("", ""), "operador");
            }

            if (lexema.getToken().equals(">") || lexema.getToken().equals("<")
                    || lexema.getToken().equals(">=") || lexema.getToken().equals("<=")
                    || lexema.getToken().equals("==") || lexema.getToken().equals("!=")) {

                expresionTest.setOperador(lexema);
                lexema = flujoTokens.avanzar();

                if (lexema.getTipoLexema().equals("Identificador")) {
                    expresionTest.setExpresion2(lexema);
                    lexema = flujoTokens.avanzar();
                    return expresionTest;
                } else {
                    throw new SintacticException(lexema, "identificador");

                }

            } else {
                flujoTokens.backTrack();
                return null;
            }
        }
        return null;

    }

}
