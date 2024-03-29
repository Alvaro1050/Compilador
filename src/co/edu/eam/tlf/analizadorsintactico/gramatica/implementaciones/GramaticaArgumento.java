/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eam.tlf.analizadorsintactico.gramatica.implementaciones;

import co.edu.eam.tlf.analizadorlexico.modelo.Lexema;
import co.edu.eam.tlf.analizadorsintactico.gramatica.definiciones.Gramatica;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Argumento;
import co.edu.eam.tlf.analizadorsintactico.sentencias.definicion.Sentencia;

/**
 *
 * @author alvar
 */
public class GramaticaArgumento implements Gramatica {

    @Override
    public Argumento analizar(Sentencia raiz, FlujoTokens flujoTokens) {

        Lexema nombre;
        Lexema lexema = flujoTokens.getTokenActual();

        if (lexema.getTipoLexema().equals("Identificador")) {
            nombre = lexema;
        } else {//si no se recibe <identificador> hay un error de sintaxis.
            return null;
        }
        //se retorna la sentencia.
        
        return new Argumento(nombre);

    }

}
