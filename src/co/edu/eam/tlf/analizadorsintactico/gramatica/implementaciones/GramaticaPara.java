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
                        if (lexema == null) {
                            throw new SintacticException(new Lexema("", ""), "}");
                        }
                        boolean continuar = true;
                        GramaticaIF gramaticaIF = new GramaticaIF();
                        GramaticaPara gramaticaPara = new GramaticaPara();
                        GramaticaDeclaradorVariable gramaticaDeclaradorVariable2 = new GramaticaDeclaradorVariable();
                        GramaticaExpresion gramaticaExpresion = new GramaticaExpresion();
                        GramaticaSentencia gramaticaSentencia = new GramaticaSentencia();

                        do {
                            lexema = flujoTokens.avanzar();
                            Sentencia sentencia = gramaticaSentencia.analizar(para, flujoTokens);
                            if (sentencia != null) {
                                lexema = flujoTokens.getTokenActual();
                                continue;

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
