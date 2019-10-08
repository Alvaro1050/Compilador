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
 * Gramatica que representa la regla de un if
 * 
* @author juan
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
                        /* se analiza lo que esta dentro del if las sentencias*/
                        boolean continuar = true;
                        GramaticaIF gramaticaIF = new GramaticaIF();
                        GramaticaPara gramaticaPara = new GramaticaPara();
                        GramaticaDeclaradorVariable gramaticaDeclaradorVariable = new GramaticaDeclaradorVariable();
                        GramaticaExpresion gramaticaExpresion = new GramaticaExpresion();

                        do {
                            lexema = flujoTokens.avanzar();
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
                            lexema = flujoTokens.avanzar();
                            boolean entro = false;
                            if (lexema.getToken().equals("contrario")) {
                                lexema = flujoTokens.avanzar();
                                entro = true;
                                GramaticaIF gramaticaIF1 = new GramaticaIF();

                                IF siIf = gramaticaIF1.analizar(si, flujoTokens);

                                if (siIf != null) {
                                    si.setContrario(siIf);
                                    lexema = flujoTokens.getTokenActual();
                                } else {

                                    if (lexema.getToken().equals("{")) {

                                        boolean continuar2 = true;
                                        GramaticaIF gramaticaIF2 = new GramaticaIF();
                                        GramaticaPara gramaticaPara2 = new GramaticaPara();
                                        GramaticaDeclaradorVariable gramaticaDeclaradorVariable2 = new GramaticaDeclaradorVariable();
                                        GramaticaExpresion gramaticaExpresion2 = new GramaticaExpresion();

                                        do {
                                            lexema = flujoTokens.avanzar();
                                            Para para2 = gramaticaPara2.analizar(si, flujoTokens);
                                            if (para2 != null) {
                                                si.getListaSentenciaSI().add(para2);
                                                continue;
                                            }

                                            DeclaradorVariable declaradorVariable2 = gramaticaDeclaradorVariable2.analizar(si, flujoTokens);

                                            if (declaradorVariable2 != null) {
                                                si.getListaSentenciaSI().add(declaradorVariable2);
                                                continue;
                                            }

                                            IF si2 = gramaticaIF2.analizar(si, flujoTokens);
                                            if (si2 != null) {
                                                si.getListaSentenciaSI().add(si);
                                                continue;
                                            }

                                            if (lexema.getToken().equals("retornar")) {
                                                lexema = flujoTokens.avanzar();

                                                Expresion expresion2 = gramaticaExpresion2.analizar(si, flujoTokens);
                                                lexema = flujoTokens.getTokenActual();
                                                if (expresion2 != null) {
                                                    si.getListaSentenciaSI().add(expresion2);
                                                    lexema = flujoTokens.getTokenActual();
                                                }

                                                if (lexema.getToken().equals(";")) {
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
                                            continuar2 = false;
                                        } while (continuar2);

                                        lexema = flujoTokens.getTokenActual();
                                        if (lexema.getToken().equals("}")) {
                                            return si;
                                        } else {
                                            throw new SintacticException(lexema, "{");
                                        }

                                    } else {
                                        throw new SintacticException(lexema, "{");
                                    }
                                }

                            }
                            if (entro) {
                                return si;
                            } else {
                                lexema = flujoTokens.retroceder();
                                return si;
                            }

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
