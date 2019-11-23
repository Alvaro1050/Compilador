/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eam.tlf.analizadorsintactico.gramatica.implementaciones;

import co.edu.eam.tlf.analizadorlexico.modelo.Lexema;
import co.edu.eam.tlf.analizadorsintactico.gramatica.definiciones.Gramatica;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.DeclaradorVariable;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Expresion;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.IF;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Para;
import co.edu.eam.tlf.analizadorsintactico.sentencias.definicion.Sentencia;

/**
 *
 * @author alvar
 */
public class GramaticaSentencia implements Gramatica {

    @Override
    public Sentencia analizar(Sentencia padre, FlujoTokens flujoTokens) {
        co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Sentencia sentencia = new co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Sentencia();

        flujoTokens.guardarPosicion();
        //primer token de la gramatica.
        Lexema lexema = flujoTokens.getTokenActual();

        GramaticaDeclaradorVariable gramaticaDeclaradorVariable = new GramaticaDeclaradorVariable();

        DeclaradorVariable dc = gramaticaDeclaradorVariable.analizar(sentencia, flujoTokens);
        if (dc != null) {
            sentencia.setDeclaradorVariable(dc);
            return sentencia;
        }
        GramaticaExpresion ge = new GramaticaExpresion();

        lexema = flujoTokens.getTokenActual();
        if (lexema.getToken().equals("retornar")) {
            lexema = flujoTokens.avanzar();

            Expresion ex = ge.analizar(sentencia, flujoTokens);

            if (ex != null) {
                sentencia.setExpresion(ex);

                lexema = flujoTokens.getTokenActual();

                if (lexema.getToken().equals(";")) {
                    return sentencia;
                }
            }
        }

        if (lexema.getToken().equals("romper") || lexema.getToken().equals("continuar")) {

            sentencia.setIdentificadorRomper(lexema);
            lexema = flujoTokens.avanzar();

            if (lexema.getTipoLexema().equals("Identificador")) {
                sentencia.setIdentificador(lexema);
                lexema = flujoTokens.avanzar();

                if (lexema.getToken().equals(";")) {
                    lexema = flujoTokens.avanzar();
                    return sentencia;
                }
            }
        }
        GramaticaIF gif = new GramaticaIF();
        IF si = gif.analizar(sentencia, flujoTokens);
        if (si != null) {
            sentencia.setSi(si);
            lexema = flujoTokens.getTokenActual();
            return sentencia;
        }

        GramaticaPara gp = new GramaticaPara();
        Para para = gp.analizar(sentencia, flujoTokens);
        if (para != null) {
            sentencia.setPara(para);
            lexema = flujoTokens.getTokenActual();

            return sentencia;
        }

        if (lexema.getToken().equals("mensaje")) {
            sentencia.setMensaje(lexema);
            lexema = flujoTokens.avanzar();

            if (lexema.getToken().equals("(")) {
                lexema = flujoTokens.avanzar();

                Expresion ex = ge.analizar(sentencia, flujoTokens);

                if (ex != null) {
                    sentencia.setExpresion2(ex);
                    lexema = flujoTokens.getTokenActual();
                }

                if (lexema.getToken().equals(")")) {
                    return sentencia;
                }

            }
        }

        if (lexema.getToken().equals("into")) {
            sentencia.setInto(lexema);
            lexema = flujoTokens.avanzar();

            if (lexema.getToken().equals("(")) {
                lexema = flujoTokens.avanzar();

                if (lexema.getTipoLexema().equals("Cadena")) {
                    sentencia.setIdentificador(lexema);
                    lexema = flujoTokens.avanzar();
                    return sentencia;
                }
            }

        }

        //ACA EMPIEZA A HACER TODO INGENIERO QUIRAMA
        //ACA EMPIEZA A HACER TODO INGENIERO QUIRAMA
        //ACA EMPIEZA A HACER TODO INGENIERO QUIRAMA
        //ACA EMPIEZA A HACER TODO INGENIERO QUIRAMA
        //ACA EMPIEZA A HACER TODO INGENIERO QUIRAMA
        //ACA EMPIEZA A HACER TODO INGENIERO QUIRAMA
        //ACA EMPIEZA A HACER TODO INGENIERO QUIRAMA
        //ACA EMPIEZA A HACER TODO INGENIERO QUIRAMA
        //ACA EMPIEZA A HACER TODO INGENIERO QUIRAMA
        //ACA EMPIEZA A HACER TODO INGENIERO QUIRAMA
        flujoTokens.backTrack();

        return null;
    }

}
