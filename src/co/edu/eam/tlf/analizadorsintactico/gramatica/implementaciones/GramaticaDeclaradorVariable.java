/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eam.tlf.analizadorsintactico.gramatica.implementaciones;

import co.edu.eam.tlf.analizadorlexico.modelo.Lexema;
import co.edu.eam.tlf.analizadorsintactico.excepciones.SintacticException;
import co.edu.eam.tlf.analizadorsintactico.gramatica.definiciones.Gramatica;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.DeclaradorVariable;
import co.edu.eam.tlf.analizadorsintactico.sentencias.definicion.Sentencia;

/**
 *
 * @author Lenovo
 */
public class GramaticaDeclaradorVariable implements Gramatica {

    @Override
    public DeclaradorVariable analizar(Sentencia padre, FlujoTokens flujoTokens) {

        DeclaradorVariable declaradorVariable = new DeclaradorVariable();

        flujoTokens.guardarPosicion();

        Lexema lexema = flujoTokens.getTokenActual();

        if (lexema.getTipoLexema().equals("Tipo Dato")) {
            declaradorVariable.setTipo(lexema);
            lexema = flujoTokens.avanzar();

            if (lexema == null) {
                throw new SintacticException(new Lexema("", ""), "identificador");

            }
            if (lexema.getTipoLexema().equals("identificador")) {

                declaradorVariable.setIdentificador(lexema);
                lexema = flujoTokens.avanzar();

                if (lexema.getToken().equals(",")) {

                    boolean continuar = true;
                    GramaticaDeclaradorVariable gramaticaDeclaradorVariable = new GramaticaDeclaradorVariable();
                    do {
                        lexema = flujoTokens.avanzar();

                        DeclaradorVariable decla = gramaticaDeclaradorVariable.analizar(declaradorVariable, flujoTokens);

                        if (decla != null) {
                            declaradorVariable.getListaDeclaradorVariable().add(decla);
                            continue;
                        }
                        continuar = false;
                    } while (continuar);

                }

                if (lexema.getToken().equals(";")) {
                    return declaradorVariable;

                }
            } else {
                //si no es identificador o tipo de dato, no es metodo, se retorna el flujo a 
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

        return null;
    }

}
