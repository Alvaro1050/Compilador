/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eam.tlf.analizadorsintactico.gramatica.implementaciones;

import co.edu.eam.tlf.analizadorlexico.modelo.Lexema;
import co.edu.eam.tlf.analizadorsintactico.gramatica.definiciones.Gramatica;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Argumento;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Atributo;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.CrearExpresion;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Lista;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Parametro;
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
            lexema = flujoTokens.getTokenActual();
            if (lexema.getTipoLexema().equals("Identificador")) {
                crearExpresion.setIdentificador(lexema);
                lexema = flujoTokens.avanzar();

                if (lexema.getToken().equals("(")) {

                    lexema = flujoTokens.getTokenActual();

                    Lista<Argumento> argumentos = new Lista<>();
                    GramaticaArgumento gramma = new GramaticaArgumento();
                    //Parametro parametro = grammma.verificar(flujoTokens);
                    /////
                    GramaticaLista<Argumento> grammaArgumentos = new GramaticaLista<>();
                    argumentos = grammaArgumentos.analizar(gramma, crearExpresion, flujoTokens, "Coma");
                    crearExpresion.setListaArgumentos(argumentos);
                    lexema = flujoTokens.getTokenActual();

                    if (lexema.getToken().equals(")")) {
                        lexema = flujoTokens.getTokenActual();

                        return crearExpresion;
                    }
                }
            }
        }
        return null;

    }

}
