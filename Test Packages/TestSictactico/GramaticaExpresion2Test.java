/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestSictactico;

import co.edu.eam.tlf.analizadorlexico.controlador.Analizador_lexico;
import co.edu.eam.tlf.analizadorlexico.modelo.Flujo_caracteres;
import co.edu.eam.tlf.analizadorsintactico.gramatica.implementaciones.FlujoTokens;
import co.edu.eam.tlf.analizadorsintactico.gramatica.implementaciones.GramaticaAtributo;
import co.edu.eam.tlf.analizadorsintactico.gramatica.implementaciones.GramaticaExpresion2;
import co.edu.eam.tlf.analizadorsintactico.gramatica.implementaciones.GramaticaIF;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Atributo;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Expresion2;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.IF;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lenovo
 */
public class GramaticaExpresion2Test {

    @Test
    public void GramaticaExpresion2Test() {
        String texto = "si(4+5|alva){retornar nuevo (nuevo(-12));}";
       char[] caracteres = texto.toCharArray();

        Flujo_caracteres fc = new Flujo_caracteres(0, caracteres);

        Analizador_lexico analizador_lexico = new Analizador_lexico();
        analizador_lexico.analizar(fc);

        FlujoTokens flujo = new FlujoTokens(analizador_lexico.getListaLexema());

        GramaticaIF granaF = new GramaticaIF();

        IF si = granaF.analizar(null, flujo);

        assertNotNull(si);

    }


}
