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
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Parametro;

/**
 * Gramatica para identificar a un parametro de un metodo. la regla sintactica
 * es: parametro::=<tipodato> <identificador>
 * tipodato::=<identificador>|<tipoprimitivo>
 *
 * @author alvar
 */
public class GramaticaParametro implements Gramatica {

    @Override
    public Parametro analizar(Sentencia raiz, FlujoTokens flujoTokens) {

        Lexema tipo, nombre;
        Lexema lexema = flujoTokens.getTokenActual();

        //si empieza con identificador o tipo de dato
        if (lexema.getTipoLexema().equals("Tipo Dato")) {
            tipo = lexema;
            lexema = flujoTokens.avanzar();
            //luego se espera indentificador.
            if (lexema.getTipoLexema().equals("Identificador")) {
                nombre = lexema;
            } else {//si no se recibe <identificador> hay un error de sintaxis.
                throw new SintacticException(lexema, "Identificador");
            }
            //se retorna la sentencia.
            return new Parametro(nombre, tipo);
        } else {
            return null;
        }
    }

}
