/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eam.tlf.analizadorsintactico.gramatica.implementaciones;

import co.edu.eam.tlf.analizadorlexico.modelo.Lexema;
import co.edu.eam.tlf.analizadorsintactico.excepciones.SintacticException;
import co.edu.eam.tlf.analizadorsintactico.gramatica.definiciones.Gramatica;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Argumento;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.CrearExpresion;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Expresion;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Lista;
import co.edu.eam.tlf.analizadorsintactico.sentencias.definicion.Sentencia;

/**
 *
 * @author Lenovo
 */
public class GramaticaCrearExpresion implements Gramatica {

    @Override
    public CrearExpresion analizar(Sentencia padre, FlujoTokens flujoTokens) {
        //Sentencia a retornar....
        CrearExpresion crearExpresion = new CrearExpresion();

        flujoTokens.guardarPosicion();
        //primer token de la gramatica.
        Lexema lexema = flujoTokens.getTokenActual();

        if (lexema.getToken().equals("nuevo")) {
            crearExpresion.setNuevo(lexema);
            lexema = flujoTokens.avanzar();
            if (lexema.getTipoLexema().equals("Identificador")) {
                crearExpresion.setIdentificador(lexema);
                lexema = flujoTokens.avanzar();

                if (lexema.getToken().equals("(")) {
                    crearExpresion.setParentesisA1(lexema);
                    lexema = flujoTokens.avanzar();

                    Lista<Argumento> argumentos = new Lista<>();
                    GramaticaArgumento gramma = new GramaticaArgumento();
                    //Parametro parametro = grammma.verificar(flujoTokens);
                    /////
                    GramaticaLista<Argumento> grammaArgumentos = new GramaticaLista<>();
                    argumentos = grammaArgumentos.analizar(gramma, crearExpresion, flujoTokens, "Coma");
                    crearExpresion.setListaArgumentos(argumentos);
                    lexema = flujoTokens.getTokenActual();

                    if (lexema.getToken().equals(")")) {
                        crearExpresion.setParentesisC1(lexema);
                        lexema = flujoTokens.getTokenActual();

                        return crearExpresion;
                    } else {
                        throw new SintacticException(lexema, ")");
                    }
                } else {
                    throw new SintacticException(lexema, "(");

                }
            }

            if (lexema.getToken().equals("(")) {
                crearExpresion.setParentesisA2(lexema);
                lexema = flujoTokens.avanzar();

                if (lexema == null) {
                    throw new SintacticException(new Lexema("", ""), "expresion");
                }

                GramaticaExpresion gramaticaExpresion = new GramaticaExpresion();

                Expresion expresion = gramaticaExpresion.analizar(crearExpresion, flujoTokens);

                if (expresion != null) {
                    crearExpresion.setExpresion(expresion);

                    lexema = flujoTokens.getTokenActual();

                    if (lexema.getToken().equals(")")) {
                        crearExpresion.setParentesisC2(lexema);
                        lexema = flujoTokens.avanzar();

                        return crearExpresion;
                    }
                } else {
                    throw new SintacticException(lexema, "expresion");
                }
            }

            if (lexema.getTipoLexema().equals("Tipo Dato")) {
                crearExpresion.setTipoEspecificador(lexema);
                lexema = flujoTokens.avanzar();

                if (lexema.getToken().equals("(")) {
                    crearExpresion.setParentesisA3(lexema);
                    lexema = flujoTokens.avanzar();

                    if (lexema == null) {
                        throw new SintacticException(new Lexema("", ""), "expresion");
                    }

                    GramaticaExpresion gramaticaExpresion = new GramaticaExpresion();

                    Expresion expresion = gramaticaExpresion.analizar(crearExpresion, flujoTokens);

                    if (expresion != null) {
                        crearExpresion.setExpresion(expresion);
                        lexema = flujoTokens.getTokenActual();

                        if (lexema.getToken().equals(")")) {
                            crearExpresion.setParentesisC3(lexema);
                            lexema = flujoTokens.avanzar();
                        }
                    } else {
                        throw new SintacticException(lexema, "expresion");

                    }

                }

                if (lexema.getToken().equals("[")) {
                    lexema = flujoTokens.avanzar();

                    if (lexema == null) {
                        throw new SintacticException(new Lexema("", ""), "]");
                    }

                    if (lexema.getToken().equals("]")) {
                        lexema = flujoTokens.avanzar();
                    } else {
                        throw new SintacticException(lexema, "]");
                    }
                }

                return crearExpresion;

            }
        }
        return null;

    }

}
