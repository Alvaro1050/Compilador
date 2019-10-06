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
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Expresion;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Expresion2;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Lista;
import co.edu.eam.tlf.analizadorsintactico.sentencias.definicion.Sentencia;

/**
 *
 * @author alvar
 */
public class GramaticaExpresion2 implements Gramatica {

    @Override
    public Expresion2 analizar(Sentencia padre, FlujoTokens flujoTokens) {
        Expresion2 expresion2 = new Expresion2();

        flujoTokens.guardarPosicion();

        Lexema lexema = flujoTokens.getTokenActual();

        if (lexema.getTipoLexema().equals("Identificador")) {
            expresion2.setIdentificador(lexema);
            lexema = flujoTokens.avanzar();
            if (lexema.getToken().equals("(")) {
                lexema = flujoTokens.avanzar();

                Lista<Argumento> argumentos = new Lista<>();
                GramaticaArgumento gramma = new GramaticaArgumento();
                //Parametro parametro = grammma.verificar(flujoTokens);
                /////
                GramaticaLista<Argumento> grammaArgumentos = new GramaticaLista<>();
                argumentos = grammaArgumentos.analizar(gramma, expresion2, flujoTokens, "Coma");
                expresion2.setListaArg(argumentos);
                lexema = flujoTokens.getTokenActual();

                if (lexema.getToken().equals(")")) {
                    lexema = flujoTokens.avanzar();
                    return expresion2;
                } else {
                    throw new SintacticException(lexema, ")");

                }
            }

            GramaticaExpresion gramaticaExpresion = new GramaticaExpresion();
            if (lexema.getToken().equals("[")) {
                Expresion expresion = gramaticaExpresion.analizar(expresion2, flujoTokens);

                if (expresion != null) {
                    expresion2.setExpresion(expresion);
                    lexema = flujoTokens.getTokenActual();
                    if (lexema.getToken().equals("]")) {
                        lexema = flujoTokens.avanzar();
                        return expresion2;
                    } else {
                        throw new SintacticException(lexema, "]");
                    }
                }
            }

            if (lexema.getToken().equals(".") || lexema.getToken().equals(",")) {
                lexema = flujoTokens.avanzar();
                Expresion expresion = gramaticaExpresion.analizar(expresion2, flujoTokens);

                if (expresion != null) {
                    expresion2.setExpresion(expresion);
                    return expresion2;
                } else {
                    throw new SintacticException(lexema, "expresion");
                }
            }
            flujoTokens.backTrack();
            return null;
        }

        return null;

    }

}
