/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eam.tlf.analizadorsintactico.gramatica.implementaciones;

import co.edu.eam.tlf.analizadorlexico.modelo.Lexema;
import co.edu.eam.tlf.analizadorsintactico.excepciones.SintacticException;
import co.edu.eam.tlf.analizadorsintactico.gramatica.definiciones.Gramatica;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.DeclaradorVariable;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Expresion;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.ExpresionLogica;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.ExpresionNumerica;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.IF;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Para;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.SentenciaToken;
import co.edu.eam.tlf.analizadorsintactico.sentencias.definicion.Sentencia;

/**
 *
 * @author juan
 */
public class GramaticaPara implements Gramatica {

    @Override
    public Para analizar(Sentencia raiz, FlujoTokens flujoTokens) {
        Para para = new Para();
        flujoTokens.guardarPosicion();

        Lexema lexema = flujoTokens.getTokenActual();

        if (lexema.getToken().equals("para")) {
            lexema = flujoTokens.avanzar();

            if (lexema == null) {
                throw new SintacticException(new Lexema("", ""), "(");
            }
            if (lexema.getToken().equals("(")) {
                lexema = flujoTokens.avanzar();

                if (lexema == null) {
                    throw new SintacticException(new Lexema("", ""), "(");
                }

                GramaticaDeclaradorVariable gramaticaDeclaradorVariable = new GramaticaDeclaradorVariable();

                DeclaradorVariable declaradorVariable = gramaticaDeclaradorVariable.analizar(para, flujoTokens);

                if (declaradorVariable != null) {
                    para.setDeclaradorVariable(declaradorVariable);
                    lexema = flujoTokens.getTokenActual();

                    GramaticaExpresionLogica gramaticaExpresionLogica = new GramaticaExpresionLogica();
                    ExpresionLogica expresionLogica = gramaticaExpresionLogica.analizar(para, flujoTokens);

                    if (expresionLogica != null) {
                        para.setExpresionLogica(expresionLogica);
                        lexema = flujoTokens.avanzar();

                        if (lexema.getToken().equals(";")) {

                            lexema = flujoTokens.avanzar();

                            if (lexema == null) {
                                throw new SintacticException(new Lexema("", ""), "Expresion numerica");
                            }

                            GramaticaExpresionNumerica gramaticaExpresionNumerica = new GramaticaExpresionNumerica();

                            ExpresionNumerica expresionNumerica = gramaticaExpresionNumerica.analizar(para, flujoTokens);

                            if (expresionNumerica != null) {
                                para.setExpresionNumerica(expresionNumerica);
                                lexema = flujoTokens.getTokenActual();
                            } else {
                                throw new SintacticException(lexema, "Expresion numerica");
                            }
                        } else {
                            throw new SintacticException(lexema, ";");
                        }

                    } else {
                        throw new SintacticException(lexema, "Expresion logica");

                    }
                } else {
                    GramaticaExpresion gramaticaExpresion = new GramaticaExpresion();

                    Expresion expresion = gramaticaExpresion.analizar(para, flujoTokens);
                    para.setExpresion(expresion);
                    lexema = flujoTokens.getTokenActual();

                    if (lexema.getToken().equals(";")) {
                        lexema = flujoTokens.avanzar();

                        GramaticaExpresionLogica gramaticaExpresionLogica = new GramaticaExpresionLogica();
                        ExpresionLogica expresionLogica = gramaticaExpresionLogica.analizar(para, flujoTokens);

                        if (expresionLogica != null) {
                            para.setExpresionLogica(expresionLogica);
                            lexema = flujoTokens.getTokenActual();

                            if (lexema.getToken().equals(";")) {

                                lexema = flujoTokens.avanzar();

                                if (lexema == null) {
                                    throw new SintacticException(new Lexema("", ""), "Expresion numerica");
                                }

                                GramaticaExpresionNumerica gramaticaExpresionNumerica = new GramaticaExpresionNumerica();

                                ExpresionNumerica expresionNumerica = gramaticaExpresionNumerica.analizar(para, flujoTokens);

                                if (expresionNumerica != null) {
                                    para.setExpresionNumerica(expresionNumerica);
                                    lexema = flujoTokens.getTokenActual();
                                } else {
                                    throw new SintacticException(lexema, "Expresion numerica");
                                }
                            } else {
                                throw new SintacticException(lexema, ";");
                            }

                        } else {
                            throw new SintacticException(lexema, "Expresion logica");

                        }
                    } else {
                        throw new SintacticException(lexema, ";");
                    }
                }

                if (lexema.getToken().equals(")")) {
                    lexema = flujoTokens.avanzar();
                    if (lexema.getToken().equals("{")) {
                        lexema = flujoTokens.avanzar();
                        if (lexema == null) {
                            throw new SintacticException(new Lexema("", ""), "}");
                        }
                        boolean continuar = true;
                        GramaticaIF gramaticaIF = new GramaticaIF();
                        GramaticaPara gramaticaPara = new GramaticaPara();
                        GramaticaDeclaradorVariable gramaticaDeclaradorVariable2 = new GramaticaDeclaradorVariable();
                        GramaticaExpresion gramaticaExpresion = new GramaticaExpresion();

                        do {
                            lexema = flujoTokens.avanzar();
                            Para para2 = gramaticaPara.analizar(para, flujoTokens);
                            if (para2 != null) {
                                para.getListaSentencia().add(para2);
                                continue;
                            }

                            DeclaradorVariable declaradorVariable2 = gramaticaDeclaradorVariable.analizar(para, flujoTokens);

                            if (declaradorVariable != null) {
                                para.getListaSentencia().add(declaradorVariable);
                                continue;
                            }

                            IF si = gramaticaIF.analizar(para, flujoTokens);
                            if (si != null) {
                                para.getListaSentencia().add(si);
                                continue;
                            }

                            if (lexema.getToken().equals("retornar")) {
                                lexema = flujoTokens.avanzar();

                                Expresion expresion = gramaticaExpresion.analizar(si, flujoTokens);
                                lexema = flujoTokens.getTokenActual();
                                if (expresion != null) {
                                    para.getListaSentencia().add(expresion);
                                    lexema = flujoTokens.getTokenActual();
                                }

                                if (lexema.getToken().equals(";")) {
                                    lexema = flujoTokens.avanzar();
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
                                    para.getListaSentencia().add(new SentenciaToken(lexema));
                                    lexema = flujoTokens.avanzar();
                                }

                                if (lexema.getToken().equals(";")) {
                                    lexema = flujoTokens.avanzar();
                                } else {
                                    throw new SintacticException(lexema, ";");
                                }
                            }
                            continuar = false;

                        } while (continuar);
                        lexema = flujoTokens.getTokenActual();
                        if (lexema.getToken().equals("}")) {
                            return para;
                        } else {
                            throw new SintacticException(lexema, "}");
                        }
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
