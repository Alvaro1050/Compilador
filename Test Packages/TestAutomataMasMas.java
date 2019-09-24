/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import co.edu.eam.tlf.analizadorlexico.controlador.Automata_mas_mas;
import co.edu.eam.tlf.analizadorlexico.modelo.Flujo_caracteres;
import co.edu.eam.tlf.analizadorlexico.modelo.Lexema;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lenovo
 */
public class TestAutomataMasMas {
  
    @Test
    public void TestAutomataMasMasCorrecto() {
        Automata_mas_mas fun = new Automata_mas_mas();
        char[] caracteres = "++".toCharArray();
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
    public void TestAutomataMasMasInCorrecto() {
       Automata_mas_mas fun = new Automata_mas_mas();
        char[] caracteres = "dhsbcdh".toCharArray();
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
