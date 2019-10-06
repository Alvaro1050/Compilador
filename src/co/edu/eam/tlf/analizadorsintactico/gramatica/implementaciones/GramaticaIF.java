package co.edu.eam.tlf.analizadorsintactico.gramatica.implementaciones;

import co.edu.eam.tlf.analizadorlexico.modelo.Lexema;
import co.edu.eam.tlf.analizadorsintactico.excepciones.SintacticException;
import co.edu.eam.tlf.analizadorsintactico.gramatica.definiciones.Gramatica;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.DeclaradorVariable;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Expresion;
import co.edu.eam.tlf.analizadorsintactico.sentencias.definicion.Sentencia;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.ExpresionLogica;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.IF;

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
                        GramaticaDeclaradorVariable gramaticaDeclaradorVariable = new GramaticaDeclaradorVariable();
                        do {

                            DeclaradorVariable declaradorVariable = gramaticaDeclaradorVariable.analizar(si, flujoTokens);

                            if (declaradorVariable != null) {
                                si.getListaSentenciaSI().add(declaradorVariable);
                                lexema = flujoTokens.getTokenActual();
                                continue;
                            }

                            if (lexema.getToken().equals("retornar")) {
                                lexema = flujoTokens.avanzar();

                                GramaticaExpresion gramaticaExpresion = new GramaticaExpresion();

                                Expresion expresion = gramaticaExpresion.analizar(si, flujoTokens);
                                lexema = flujoTokens.getTokenActual();
                                if (expresion != null) {
                                    si.getListaSentenciaSI().add(expresion);
                                    lexema = flujoTokens.getTokenActual();
                                }   

                                if (lexema.getToken().equals(";")) {
                                    lexema = flujoTokens.avanzar();
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
