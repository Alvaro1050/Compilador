/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eam.tlf.analizadorsintactico.gramatica.implementaciones;

import co.edu.eam.tlf.analizadorsintactico.gramatica.definiciones.Gramatica;
import co.edu.eam.tlf.analizadorlexico.modelo.Lexema;
import co.edu.eam.tlf.analizadorsintactico.excepciones.SintacticException;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Lista;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.IF;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Metodo;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Parametro;
import co.edu.eam.tlf.analizadorsintactico.sentencias.definicion.Sentencia;

/**
 * Clase que represetna la gramatica de un metodo.
 *
 * @author alvar
 */
public class GramaticaMetodoDeclaracion implements Gramatica {

    @Override
    public Metodo analizar(Sentencia raiz, FlujoTokens flujoTokens) {
        //Sentencia a retornar....
        Metodo metodo = new Metodo();
        flujoTokens.guardarPosicion();
        //primer token de la gramatica.
        Lexema lexema = flujoTokens.getTokenActual();

        //tipo de retorno.....
        if (lexema.getTipoLexema().equals("Tipo Dato")) {
            metodo.setRetorno(lexema);
            lexema = flujoTokens.avanzar();
            if (lexema == null) {
                throw new SintacticException(new Lexema("", ""), "Identificador");
            }
            //nombre del metodo....
            if (lexema.getTipoLexema().equals("Identificador")) {
                metodo.setNombre(lexema);
                lexema = flujoTokens.avanzar();
            } else {
                //si no es identificador, no es metodo, se retorna el flujo a 
                //la posicion inicial
                flujoTokens.backTrack();
                return null; //se retorna null para que se pruebe con otra regal..
            }

            //parentesis.......
            if (lexema.getTipoLexema().equals("parentesis abierto")) {
                //lista de parametros......

                Lista<Parametro> parametros = new Lista<>();
                GramaticaParametro gramma = new GramaticaParametro();
                //Parametro parametro = grammma.verificar(flujoTokens);
                /////
                GramaticaLista<Parametro> grammaParametros = new GramaticaLista<>();
                parametros = grammaParametros.analizar(gramma, metodo, flujoTokens, "Coma");
                metodo.setListaParametros(parametros);
                lexema = flujoTokens.getTokenActual();
                if (lexema.getTipoLexema().equals("parentesis cerrado")) {
                    metodo.setListaParametros(parametros);//se setean los parametros.
                    lexema = flujoTokens.avanzar();
                    //se espera llave abierta.....
                    if (lexema.getTipoLexema().equals("corchete abierto")) {
                        //se analiza el cuerpo del metodo.....
                        boolean continuar = true;
                        GramaticaIF gramaticaIF = new GramaticaIF();
                        GramaticaMetodoDeclaracion gramaticaMetodo = new GramaticaMetodoDeclaracion();
                        do {
                            lexema = flujoTokens.avanzar();
                            Metodo met = gramaticaMetodo.analizar(metodo, flujoTokens);
                            if (met != null) {
                                metodo.getListaSentencias().add(met);
                                continue;
                            }

                            IF si = gramaticaIF.analizar(metodo, flujoTokens);
                            if (si != null) {
                                metodo.getListaSentencias().add(si);
                                continue;
                            }
                            continuar = false;

                        } while (continuar);
                        //se acabo el metodo.....
                        if (lexema.getTipoLexema().equals("corchete cerrado")) {

                            return metodo;
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