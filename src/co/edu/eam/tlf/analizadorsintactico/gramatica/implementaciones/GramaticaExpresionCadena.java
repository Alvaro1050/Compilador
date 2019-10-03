/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eam.tlf.analizadorsintactico.gramatica.implementaciones;

import co.edu.eam.tlf.analizadorlexico.modelo.Lexema;
import co.edu.eam.tlf.analizadorsintactico.excepciones.SintacticException;
import co.edu.eam.tlf.analizadorsintactico.gramatica.definiciones.Gramatica;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.ExpresionCadena;
import co.edu.eam.tlf.analizadorsintactico.sentencias.definicion.Sentencia;

/**
 *
 * @author Lenovo
 */
public class GramaticaExpresionCadena implements Gramatica{

    @Override
    public ExpresionCadena analizar(Sentencia padre, FlujoTokens flujoTokens) {

      ExpresionCadena expresionCadena= new ExpresionCadena();

        flujoTokens.guardarPosicion();

        Lexema lexema = flujoTokens.getTokenActual();

        if (lexema.getTipoLexema().equals("Identificador")) {
            expresionCadena.setExpresion1(lexema);
            lexema = flujoTokens.avanzar();

            if (lexema == null) {
                throw new SintacticException(new Lexema("", ""), "operador");
            }

            if (lexema.getToken().equals("+") || lexema.getToken().equals("+=")) {

                expresionCadena.setOperador(lexema);
                lexema = flujoTokens.avanzar();

                if (lexema.getTipoLexema().equals("Identificador")) {
                    expresionCadena.setExpresion2(lexema);
                    lexema = flujoTokens.avanzar();
                    return expresionCadena;
                } else {
                    throw new SintacticException(lexema, "identificador");

                }

            } else {
                throw new SintacticException(lexema, "operador");

            }
        }
        return null;

    }

}
