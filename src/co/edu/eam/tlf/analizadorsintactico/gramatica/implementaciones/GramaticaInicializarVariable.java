/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eam.tlf.analizadorsintactico.gramatica.implementaciones;

import co.edu.eam.tlf.analizadorlexico.modelo.Lexema;
import co.edu.eam.tlf.analizadorsintactico.excepciones.SintacticException;
import co.edu.eam.tlf.analizadorsintactico.gramatica.definiciones.Gramatica;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.InicializarVariable;
import co.edu.eam.tlf.analizadorsintactico.sentencias.definicion.Sentencia;

/**
 *
 * @author Lenovo
 */
public class GramaticaInicializarVariable implements Gramatica {

    @Override
    public InicializarVariable analizar(Sentencia padre, FlujoTokens flujoTokens) {

        InicializarVariable inicializarVariable = new InicializarVariable();

        flujoTokens.guardarPosicion();

        Lexema lexema = flujoTokens.getTokenActual();

        if (lexema.getTipoLexema().equals("Identificador")) {
            inicializarVariable.setVariable(lexema);
            lexema = flujoTokens.avanzar();

            if (lexema == null) {
                throw new SintacticException(new Lexema("", ""), "Identificador");

            }
            if (lexema.getToken().equals("=")) {

                lexema = flujoTokens.avanzar();

                if (lexema.getTipoLexema().equals("Identificador")) {

                }

            } else {
                //si no es identificador o tipo de dato, no es metodo, se retorna el flujo a 
                //la posicion inicial
                flujoTokens.backTrack();
                return null; //se retorna null para que se pruebe con otra regal..
            }

            return null;
        }

        return null;

    }
}
