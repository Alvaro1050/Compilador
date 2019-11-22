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
                        GramaticaSentencia gramaticaSentencia = new GramaticaSentencia();

                        do {
                            lexema = flujoTokens.avanzar();
                            Sentencia sentencia = gramaticaSentencia.analizar(si, flujoTokens);
                            if (sentencia != null) {
                                lexema = flujoTokens.getTokenActual();
                                continue;

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

                                        do {
                                            lexema = flujoTokens.avanzar();
                                            Sentencia sentencia = gramaticaSentencia.analizar(si, flujoTokens);
                                            if (sentencia != null) {
                                                lexema = flujoTokens.getTokenActual();
                                                si.getListaSentenciaSI().add(sentencia);
                                                continue;

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
