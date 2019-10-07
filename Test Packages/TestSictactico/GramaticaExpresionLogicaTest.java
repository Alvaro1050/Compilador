/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestSictactico;

import co.edu.eam.tlf.analizadorlexico.controlador.Analizador_lexico;
import co.edu.eam.tlf.analizadorlexico.modelo.Flujo_caracteres;
import co.edu.eam.tlf.analizadorsintactico.gramatica.implementaciones.FlujoTokens;
import co.edu.eam.tlf.analizadorsintactico.gramatica.implementaciones.GramaticaExpresionLogica;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.ExpresionLogica;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lenovo
 */
public class GramaticaExpresionLogicaTest {
    
    @Test
    public void GramaticaExpresionLogicaTest() {
    String texto = "4+4 | 5+5";
        char[] caracteres = texto.toCharArray();

        Flujo_caracteres fc = new Flujo_caracteres(0, caracteres);

        Analizador_lexico analizador_lexico = new Analizador_lexico();
        analizador_lexico.analizar(fc);

        FlujoTokens flujo = new FlujoTokens(analizador_lexico.getListaLexema());

        GramaticaExpresionLogica gramaticaExpresionLogica = new GramaticaExpresionLogica();

        ExpresionLogica expresionLogica = gramaticaExpresionLogica.analizar(null, flujo);

        assertNotNull(expresionLogica);

    }


}
