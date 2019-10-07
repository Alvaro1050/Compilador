
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eam.tlf.analizadorsintactico.gramatica.implementaciones;

import co.edu.eam.tlf.analizadorsintactico.gramatica.definiciones.Gramatica;
import co.edu.eam.tlf.analizadorlexico.modelo.Lexema;
import co.edu.eam.tlf.analizadorsintactico.excepciones.SintacticException;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Constructor;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.DeclaradorVariable;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Expresion;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Lista;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.IF;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Para;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Parametro;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.SentenciaToken;
import co.edu.eam.tlf.analizadorsintactico.sentencias.definicion.Sentencia;

/**
 * Clase que represetna la gramatica de un metodo.
 *
 * @author juan
 */
public class GramaticaDeclararConstructor implements Gramatica {

    @Override
    public Constructor analizar(Sentencia raiz, FlujoTokens flujoTokens) {
        //Sentencia a retornar....
        Constructor constructor = new Constructor();
        flujoTokens.guardarPosicion();
        //primer token de la gramatica.
        Lexema lexema = flujoTokens.getTokenActual();

        //nombre del constructor.....
        if (lexema.getTipoLexema().equals("Identificador")) {
            constructor.setNombre(lexema);
            lexema = flujoTokens.avanzar();
            if (lexema == null) {
                throw new SintacticException(new Lexema("", ""), "Identificador");
            }
            //parentesis.......
            if (lexema.getTipoLexema().equals("parentesis abierto")) {
                //lista de parametros......

                Lista<Parametro> parametros = new Lista<>();
                GramaticaParametro gramma = new GramaticaParametro();
                //Parametro parametro = grammma.verificar(flujoTokens);
                /////
                GramaticaLista<Parametro> grammaParametros = new GramaticaLista<>();
                parametros = grammaParametros.analizar(gramma, constructor, flujoTokens, "Coma");
                constructor.setListaParametros(parametros);
                lexema = flujoTokens.getTokenActual();

                if (lexema == null) {
                    throw new SintacticException(new Lexema("", ""), "parentesis cerrado");
                }
                if (lexema.getTipoLexema().equals("parentesis cerrado")) {
                    lexema = flujoTokens.avanzar();
                    //se espera llave abierta.....
                    if (lexema == null) {
                        throw new SintacticException(new Lexema("", ""), "corchete abierto");

                    }
                    if (lexema.getTipoLexema().equals("corchete abierto")) {
                        //se analiza el cuerpo del metodo.....
                        boolean continuar = true;
                        GramaticaIF gramaticaIF = new GramaticaIF();
                        GramaticaPara gramaticaPara = new GramaticaPara();
                        GramaticaDeclaradorVariable gramaticaDeclaradorVariable = new GramaticaDeclaradorVariable();
                        GramaticaExpresion gramaticaExpresion = new GramaticaExpresion();

                        do {
                            lexema = flujoTokens.avanzar();
                            Para para = gramaticaPara.analizar(constructor, flujoTokens);
                            if (para != null) {
                                constructor.getListaSentencia().add(para);
                                continue;
                            }

                            DeclaradorVariable declaradorVariable = gramaticaDeclaradorVariable.analizar(constructor, flujoTokens);

                            if (declaradorVariable != null) {
                                constructor.getListaSentencia().add(declaradorVariable);
                                continue;
                            }

                            IF si = gramaticaIF.analizar(constructor, flujoTokens);
                            if (si != null) {
                                constructor.getListaSentencia().add(si);
                                continue;
                            }

                            if (lexema.getToken().equals("retornar")) {
                                lexema = flujoTokens.avanzar();

                                Expresion expresion = gramaticaExpresion.analizar(constructor, flujoTokens);
                                lexema = flujoTokens.getTokenActual();
                                if (expresion != null) {
                                    constructor.getListaSentencia().add(expresion);
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
                                    constructor.getListaSentencia().add(new SentenciaToken(lexema));
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
                        //se acabo el metodo.....
                        if (lexema.getTipoLexema().equals("corchete cerrado")) {

                            return constructor;
                        } else {//si no se termina con llave cerrada, excepcion...
                            throw new SintacticException(lexema, "Llave cerrada");
                        }

                    } else {//si no se empieza con llave abierta, error.
                        throw new SintacticException(lexema, "Llave abierta");
                    }

                } else {
                    throw new SintacticException(lexema, "parentesis cerrado");
                }

            } else {
                //si no es identificador, no es metodo, se retorna el flujo a 
                //la posicion inicial
                flujoTokens.backTrack();
                return null; //se retorna null para que se pruebe con otra regal..
            }

        } else {
            //si no es identificador o tipo de dato, no es metodo, se retorna el flujo a 
            //la posicion inicial
            flujoTokens.backTrack();
            return null; //se retorna null para que se pruebe con otra regal..
        }

    }

}
