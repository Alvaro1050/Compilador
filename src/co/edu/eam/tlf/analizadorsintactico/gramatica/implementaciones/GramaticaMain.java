/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eam.tlf.analizadorsintactico.gramatica.implementaciones;

import co.edu.eam.tlf.analizadorsintactico.gramatica.definiciones.Gramatica;
import co.edu.eam.tlf.analizadorlexico.modelo.Lexema;
import co.edu.eam.tlf.analizadorsintactico.excepciones.SintacticException;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Atributo;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Constructor;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Lista;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.IF;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Main;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Metodo;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Parametro;
import co.edu.eam.tlf.analizadorsintactico.sentencias.definicion.Sentencia;

/**
 * Clase que represetna la gramatica de un metodo.
 *
 * @author alvar
 */
public class GramaticaMain implements Gramatica {

    @Override
    public Main analizar(Sentencia raiz, FlujoTokens flujoTokens) {
        //Sentencia a retornar....
        Main main = new Main();
        flujoTokens.guardarPosicion();
        //primer token de la gramatica.
        Lexema lexema = flujoTokens.getTokenActual();

        //nombre del constructor.....
        if (lexema.getTipoLexema().equals("Tipo Dato") || lexema.getToken().equals("vacio")) {
            main.setRetorno(lexema);
            lexema = flujoTokens.avanzar();
            if (lexema == null) {
                throw new SintacticException(new Lexema("", ""), "main");
            }
            //parentesis.......
            if (lexema.getToken().equals("main")) {
                main.setMain(lexema);
                lexema = flujoTokens.avanzar();

                if (lexema == null) {
                    throw new SintacticException(new Lexema("", ""), "(");
                }

                if (lexema.getToken().equals("(")) {
                    lexema = flujoTokens.avanzar();

                    if (lexema == null) {
                        throw new SintacticException(new Lexema("", ""), "(");
                    }

                    if (lexema.getTipoLexema().equals("Tipo Dato")) {
                        main.setTipoIdentidicador(lexema);
                        lexema = flujoTokens.avanzar();

                        if (lexema == null) {
                            throw new SintacticException(new Lexema("", ""), "[");
                        }

                        if (lexema.getToken().equals("[")) {
                            lexema = flujoTokens.avanzar();

                            if (lexema == null) {
                                throw new SintacticException(new Lexema("", ""), "]");
                            }

                            if (lexema.getToken().equals("]")) {
                                lexema = flujoTokens.avanzar();
                                if (lexema == null) {
                                    throw new SintacticException(new Lexema("", ""), "identificador");
                                }

                                if (lexema.getTipoLexema().equals("Identificador")) {
                                    main.setNombreParametro(lexema);
                                    lexema = flujoTokens.avanzar();

                                    if (lexema == null) {
                                        throw new SintacticException(new Lexema("", ""), ")");
                                    }

                                    if (lexema.getToken().equals(")")) {
                                        lexema = flujoTokens.avanzar();

                                        if (lexema == null) {
                                            throw new SintacticException(new Lexema("", ""), "{");
                                        }

                                        if (lexema.getToken().equals("{")) {
                                            /*se analiza las sentencias del main*/

                                            boolean continuar = true;
                                            GramaticaAtributo gramaticaAtributo = new GramaticaAtributo();
                                            do {
                                                lexema = flujoTokens.avanzar();
                                                Atributo atributo = gramaticaAtributo.analizar(main, flujoTokens);
                                                if (atributo != null) {
                                                    main.getListaSentencia().add(atributo);
                                                    continue;
                                                }

                                                continuar = false;

                                            } while (continuar);

                                            if (lexema.getToken().equals("}")) {

                                                return main;
                                            } else {//si no se termina con llave cerrada, excepcion...
                                                throw new SintacticException(lexema, "}");
                                            }

                                        } else {
                                            throw new SintacticException(lexema, "{");

                                        }
                                    } else {
                                        throw new SintacticException(lexema, ")");

                                    }
                                } else {
                                    throw new SintacticException(lexema, "Identificador");

                                }

                            } else {
                                throw new SintacticException(lexema, "]");

                            }

                        } else {
                            throw new SintacticException(lexema, "[");

                        }

                    } else {
                        throw new SintacticException(lexema, "Tipo Dato");

                    }

                } else {
                    throw new SintacticException(lexema, "(");

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
