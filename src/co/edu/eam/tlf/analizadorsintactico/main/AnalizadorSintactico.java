/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eam.tlf.analizadorsintactico.main;

import co.edu.eam.tlf.analizadorlexico.controlador.Analizador_lexico;
import co.edu.eam.tlf.analizadorlexico.modelo.Flujo_caracteres;
import co.edu.eam.tlf.analizadorsintactico.gramatica.implementaciones.FlujoTokens;
import co.edu.eam.tlf.analizadorlexico.modelo.Lexema;
import co.edu.eam.tlf.analizadorlexico.vista.FrmAnalizar;
import co.edu.eam.tlf.analizadorsintactico.gramatica.implementaciones.GramaticaClase;
import co.edu.eam.tlf.analizadorsintactico.sentencias.definicion.Sentencia;
import java.util.List;

/**
 *
 * @author alvaro
 */
public class AnalizadorSintactico {

    /**
     * Analizador Lexico
     */
    private final Analizador_lexico analizadorLexico;

    /**
     * Raiz del arbol de derivacion
     */
    private Sentencia unidadCompilacion;
    private char[] caracteres;

    List<Lexema> tokens;
    List<Lexema> errores;

    public List<Lexema> getTokens() {
        return tokens;
    }

    public void setTokens(List<Lexema> tokens) {
        this.tokens = tokens;
    }

    public List<Lexema> getErrores() {
        return errores;
    }

    public void setErrores(List<Lexema> errores) {
        this.errores = errores;
    }
    
    
    

    /**
     * Constrctor
     */
    public AnalizadorSintactico() {
        analizadorLexico = new Analizador_lexico();
    }

    /**
     * Metodo para analizar el codigo sintacticamente
     *
     * @param codigo
     */
    public void analizar(String codigo) {
        caracteres = codigo.toCharArray();

        Flujo_caracteres fc = new Flujo_caracteres(0, caracteres);
        analizadorLexico.analizar(fc);
        tokens = analizadorLexico.getListaLexema();
        errores = analizadorLexico.getListaErrores();

        FrmAnalizar.listar();
        FrmAnalizar.listarErrores();
        //si no hay errores, se continua con el analisis semantico.
        if (errores.isEmpty()) {
            FlujoTokens flujo = new FlujoTokens(tokens);
            GramaticaClase gramm = new GramaticaClase();

            unidadCompilacion = gramm.analizar(null, flujo);

            unidadCompilacion.llenarHijos();

            System.out.println(unidadCompilacion.getHijos());

        }
    }

    public Analizador_lexico getAnalizadorLexico() {
        return analizadorLexico;
    }

    public Sentencia getUnidadCompilacion() {
        return unidadCompilacion;
    }

}
