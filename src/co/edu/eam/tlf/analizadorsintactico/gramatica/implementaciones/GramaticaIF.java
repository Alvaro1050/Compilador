package co.edu.eam.tlf.analizadorsintactico.gramatica.implementaciones;

import co.edu.eam.tlf.analizadorlexico.modelo.Lexema;
import co.edu.eam.tlf.analizadorsintactico.excepciones.SintacticException;
import co.edu.eam.tlf.analizadorsintactico.gramatica.definiciones.Gramatica;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.DeclaradorVariable;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Expresion;
import co.edu.eam.tlf.analizadorsintactico.sentencias.definicion.Sentencia;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.ExpresionLogica;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.IF;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Para;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.SentenciaToken;

/**
*Gramatica que representa la regla de un if
*
*@author juan
*/

public class GramaticaIF implements Gramatica {

    @Override
    public IF analizar(Sentencia raiz, FlujoTokens flujoTokens) {
        IF si = new IF();
        flujoTokens.guardarPosicion();

        Lexema lexema = flujoTokens.getTokenActual();

        if (lexema.getToken().equals("si")) {
            lexema = flujoTokens.avanzar();
            if (lexema == null) {
                throw new SintacticException(new Lexema("", ""), "(");
            } else if (lexema.getToken().equals("(")) {
                GramaticaExpresionLogica gramaticaExpresionLogica = new GramaticaExpresionLogica();
                lexema = flujoTokens.avanzar();
                ExpresionLogica expresionLogica = gramaticaExpresionLogica.analizar(si, flujoTokens);

                if (expresionLogica != null) {
                    si.setCondicion(expresionLogica);
                    lexema = flujoTokens.getTokenActual();
                } else {
                    throw new SintacticException(lexema, "condicion");
                }

                if (lexema.getToken().equals(")")) {
                    lexema = flujoTokens.avanzar();
                    if (lexema.getToken().equals("{")) {
                        lexema = flujoTokens.avanzar();
                        /* se analiza lo que esta dentro del if las sentencias*/
                        boolean continuar = true;
                        GramaticaIF gramaticaIF = new GramaticaIF();
                        GramaticaPara gramaticaPara = new GramaticaPara();
                        GramaticaDeclaradorVariable gramaticaDeclaradorVariable = new GramaticaDeclaradorVariable();
                        GramaticaExpresion gramaticaExpresion = new GramaticaExpresion();

                        do {
                            lexema = flujoTokens.getTokenActual();
                            Para para = gramaticaPara.analizar(si, flujoTokens);
                            if (para != null) {
                                si.getListaSentenciaSI().add(para);
                                continue;
                            }

                            DeclaradorVariable declaradorVariable = gramaticaDeclaradorVariable.analizar(si, flujoTokens);

                            if (declaradorVariable != null) {
                                si.getListaSentenciaSI().add(declaradorVariable);
                                continue;
                            }

                            IF si2 = gramaticaIF.analizar(si, flujoTokens);
                            if (si2 != null) {
                                si.getListaSentenciaSI().add(si);
                                continue;
                            }

                            if (lexema.getToken().equals("retornar")) {
                                lexema = flujoTokens.avanzar();

                                Expresion expresion = gramaticaExpresion.analizar(si, flujoTokens);
                                lexema = flujoTokens.getTokenActual();
                                if (expresion != null) {
                                    si.getListaSentenciaSI().add(expresion);
                                    lexema = flujoTokens.getTokenActual();
                                }

                                if (lexema.getToken().equals(";")) {
                                    lexema = flujoTokens.avanzar();
                                    continue;
                                } else {
                                    throw new SintacticException(lexema, ";");

                                }
                            }

                            if (lexema.getToken().equals("romper") || lexema.getToken().equals("continue")) {
                                lexema = flujoTokens.avanzar();

                                if (lexema == null) {
                                    throw new SintacticException(new Lexema("", ""), ";");
                                }

                                if (lexema.getTipoLexema().equals("Identificador")) {
                                    si.getListaSentenciaSI().add(new SentenciaToken(lexema));
                                    lexema = flujoTokens.avanzar();
                                }

                                if (lexema.getToken().equals(";")) {
                                    lexema = flujoTokens.avanzar();
                                    continue;
                                } else {
                                    throw new SintacticException(lexema, ";");
                                }
                            }
                            continuar = false;

                        } while (continuar);

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
