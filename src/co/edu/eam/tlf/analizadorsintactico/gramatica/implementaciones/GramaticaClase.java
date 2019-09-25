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
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Metodo;
import java.util.List;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreePath;

/**
 * Gramatica que representa la regla de una clase.
 *
 * @author alvaro
 */
public class GramaticaClase implements Gramatica {

    @Override
    public Clase analizar(Sentencia raiz, FlujoTokens flujoTokens) {

        Clase clase = new Clase();
        Lexema lexema = flujoTokens.getTokenActual();

        //palabra reservada class....
        if (lexema.getToken().equals("clase")) {
            lexema = flujoTokens.avanzar();
            if (lexema.getTipoLexema().equals("Identificador")) {
                clase.setNombreClase(lexema);
                lexema = flujoTokens.avanzar();

                if (lexema.getTipoLexema().equals("corchete abierto")) {
                    //se analiza el cuerpo del metodo.....
                    boolean continuar = true;
                    GramaticaAtributo gramaticaAtributo = new GramaticaAtributo();
                    GramaticaMetodo gramaticaMetodo = new GramaticaMetodo();
                    do {
                        lexema = flujoTokens.avanzar();
                        Metodo met = gramaticaMetodo.analizar(clase, flujoTokens);
                        if (met != null) {
                            clase.getListaMetodos().add(met);
                            continue;
                        }

                        Atributo atributo = gramaticaAtributo.analizar(clase, flujoTokens);
                        if (atributo != null) {
                            clase.getListaAtributos().add(atributo);
                            continue;
                        }
                        continuar = false;

                    } while (continuar);

                    //se acabo el metodo.....
                    if (lexema.getTipoLexema().equals("Llave cerrada")) {

                        return clase;
                    } else {//si no se termina con llave cerrada, excepcion...
                        throw new SintacticException(lexema, "Llave cerrada");
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
