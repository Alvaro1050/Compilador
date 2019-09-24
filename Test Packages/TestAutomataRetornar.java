/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import co.edu.eam.tlf.analizadorlexico.controlador.Automata_retornar;
import co.edu.eam.tlf.analizadorlexico.modelo.Flujo_caracteres;
import co.edu.eam.tlf.analizadorlexico.modelo.Lexema;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lenovo
 */
public class TestAutomataRetornar {
    
   @Test
    public void TestAutomataRetornarCorrecto() {
         Automata_retornar fun = new Automata_retornar();
        char[] caracteres = "retornar".toCharArray();
        Lexema resultado = fun.inicio(new Flujo_caracteres(0, caracteres));
        int esperado = 1;
        int resultadoMetodo;
        if (resultado != null) {
            resultadoMetodo = 1;
        } else {
            resultadoMetodo = 0;
        }

        assertEquals(esperado, resultadoMetodo);

    }

    @Test
    public void TestAutomataRetornarInCorrecto() {
       Automata_retornar fun = new Automata_retornar();
        char[] caracteres = "retornardsdsa".toCharArray();
        Lexema resultado = fun.inicio(new Flujo_caracteres(0, caracteres));
        int esperado = 0;
        int resultadoMetodo;
        if (resultado != null) {
            resultadoMetodo = 1;
        } else {
            resultadoMetodo = 0;
        }

        assertEquals(esperado, resultadoMetodo);

    }
    
}
