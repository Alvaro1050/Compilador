/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eam.tlf.analizadorsintactico.gramatica.implementaciones;

import co.edu.eam.tlf.analizadorsintactico.gramatica.definiciones.Gramatica;
import co.edu.eam.tlf.analizadorlexico.modelo.Lexema;
import co.edu.eam.tlf.analizadorsintactico.excepciones.SintacticException;
import co.edu.eam.tlf.analizadorsintactico.sentencias.definicion.Sentencia;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Atributo;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Clase;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Constructor;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.DeclaradorVariable;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Expresion;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Main;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Metodo;

/**
 * Gramatica que representa la regla de una clase.
 *
 * @author juan
 */
public class GramaticaClase implements Gramatica {

    @Override
    public Clase analizar(Sentencia raiz, FlujoTokens flujoTokens) {

        Clase clase = new Clase();
        Lexema lexema = flujoTokens.getTokenActual();

        //palabra reservada class....
        if (lexema.getToken().equals("clase")) {
            lexema = flujoTokens.avanzar();
            if (lexema == null) {
                throw new SintacticException(new Lexema("", ""), "Identificador");
            } else if (lexema.getTipoLexema().equals("Identificador")) {
                clase.setNombreClase(lexema);
                lexema = flujoTokens.avanzar();
                if (lexema == null) {
                    throw new SintacticException(new Lexema("", ""), "{");
                }
                if (lexema.getTipoLexema().equals("corchete abierto")) {
                    //se analiza el cuerpo del metodo.....
                    boolean continuar = true;
                    GramaticaAtributo gramaticaAtributo = new GramaticaAtributo();
                    GramaticaMetodoDeclaracion gramaticaMetodo = new GramaticaMetodoDeclaracion();
                    GramaticaDeclararConstructor gramaticaConstructor = new GramaticaDeclararConstructor();
                    GramaticaMain gramaticaMain = new GramaticaMain();
                    GramaticaDeclaradorVariable gramaticaDeclaradorVariable = new GramaticaDeclaradorVariable();
                    /* prueba gramatica expresion*/

                    GramaticaExpresion gramaticaExpresion = new GramaticaExpresion();
                    do {
                        lexema = flujoTokens.avanzar();
                        Metodo met = gramaticaMetodo.analizar(clase, flujoTokens);
                        if (met != null) {
                            clase.getListaMetodos().add(met);
                            continue;
                        }

                        Expresion expresion = gramaticaExpresion.analizar(clase, flujoTokens);

                        if (expresion != null) {
                            clase.getListaSentencia().add(expresion);
                            lexema = flujoTokens.getTokenActual();
                            lexema = flujoTokens.retroceder();
                            continue;
                        }
                        Atributo atributo = gramaticaAtributo.analizar(clase, flujoTokens);
                        if (atributo != null) {
                            clase.getListaAtributos().add(atributo);
                            continue;
                        }

                        Constructor constructor = gramaticaConstructor.analizar(clase, flujoTokens);
                        if (constructor != null) {
                            clase.getListaSentencia().add(constructor);
                            continue;

                        }

                        Main main = gramaticaMain.analizar(clase, flujoTokens);
                        if (main != null) {
                            clase.getListaSentencia().add(main);
                            continue;

                        }

                        DeclaradorVariable declaradorVariable = gramaticaDeclaradorVariable.analizar(clase, flujoTokens);
                        if (declaradorVariable != null) {
                            clase.getListaSentencia().add(declaradorVariable);
                            continue;

                        }

                        continuar = false;

                    } while (continuar);

                    //se acabo el metodo.....
                    if (lexema.getTipoLexema().equals("corchete cerrado")) {
                        lexema = flujoTokens.avanzar();
                        if (lexema != null) {
                            throw new SintacticException(lexema, "la clase finalizada");

                        } else {
                            return clase;
                        }
                    } else {//si no se termina con llave cerrada, excepcion...
                        throw new SintacticException(lexema, "corchete cerrado");
                    }

                } else {//si no se empieza con llave abierta, error.
                    throw new SintacticException(lexema, "Llave abierta");
                }
            }

        } else {
            flujoTokens.backTrack();
            return null;
        }
        return null;
    }

}
