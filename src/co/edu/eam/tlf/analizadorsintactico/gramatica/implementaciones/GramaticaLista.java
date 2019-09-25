/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eam.tlf.analizadorsintactico.gramatica.implementaciones;

import co.edu.eam.tlf.analizadorsintactico.gramatica.definiciones.Gramatica;
import co.edu.eam.tlf.analizadorlexico.modelo.Lexema;
import co.edu.eam.tlf.analizadorsintactico.sentencias.definicion.Sentencia;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Atributo;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Lista;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Metodo;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreePath;

/**
 * Clase que representa a un atributo como elemeto gramatical.
 *
 * @author alvar
 */
public class GramaticaLista<T extends Sentencia> {

    /**
     * Metodo que analiza el flujo de tokens buscando lista de sentencias
     * separadas por algun token en especial.
     *
     * @param flujo, flujo de tokens...
     * @return la lista de sentencias o null si no esta.
     */
    public Lista<T> analizar(Gramatica gramma, Sentencia raiz, FlujoTokens flujoTokens, String separador) {

        List<T> sentencias = new ArrayList<>();
        T parametro = null;
        boolean go = true;
        do {
            Lexema lexema = flujoTokens.avanzar();
            parametro = (T) gramma.analizar(raiz, flujoTokens);
            if (parametro != null) {
                sentencias.add(parametro);
                lexema = flujoTokens.avanzar();

                if (lexema.getTipoLexema() != separador) {
                    break;
                }
            } else {
                go = false;
            }

        } while (go);

        return new Lista<T>(sentencias);
    }

}
