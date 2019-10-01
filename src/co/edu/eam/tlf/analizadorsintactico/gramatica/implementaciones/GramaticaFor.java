/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eam.tlf.analizadorsintactico.gramatica.implementaciones;

import co.edu.eam.tlf.analizadorlexico.modelo.Lexema;
import co.edu.eam.tlf.analizadorsintactico.excepciones.SintacticException;
import co.edu.eam.tlf.analizadorsintactico.gramatica.definiciones.Gramatica;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.IF;
import co.edu.eam.tlf.analizadorsintactico.sentencias.definicion.Sentencia;

/**
 *
 * @author alvar
 */
public class GramaticaFor implements Gramatica {

    @Override
    public IF analizar(Sentencia raiz, FlujoTokens flujoTokens) {
        IF si = new IF();
        flujoTokens.guardarPosicion();

        Lexema lexema = flujoTokens.getTokenActual();

        if (lexema.getToken().equals("para")) {
            lexema = flujoTokens.avanzar();
            if (lexema == null) {
                throw new SintacticException(new Lexema("", ""), "(");
            } else if (lexema.getToken().equals("(")) {
                /*  GramaticaCondicion gc = new GramaticaCondicion();
                 lexema = flujoTokens.avanzar();
                 Condicion condicion = gc.analizar(si, flujoTokens);

                 if (condicion != null) {
                 si.setCondicion(condicion);
                 lexema = flujoTokens.avanzar();
                 } else if (lexema == null) {
                 throw new SintacticException(new Lexema("", ""), ")");
                 }*/
                lexema = flujoTokens.avanzar();

                if (lexema.getToken().equals(")")) {
                    lexema = flujoTokens.avanzar();
                    if (lexema.getToken().equals("{")) {
                        lexema = flujoTokens.avanzar();
                        /* se analiza el cuerpo del for todas las sentencias que puedan haber dentro*/
                        if (lexema.getToken().equals("}")) {
                            return si;
                        }
                    } else if (lexema == null) {
                        throw new SintacticException(new Lexema("", ""), "}");
                    }
                } else if (lexema == null) {
                    throw new SintacticException(new Lexema("", ""), "{");
                }

            } else {
                throw new SintacticException(lexema, "(");
            }

        } else {
            flujoTokens.backTrack();
            return null;
        }

        return null;

    }
}