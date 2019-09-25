/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eam.tlf.analizadorsintactico.excepciones;

import co.edu.eam.tlf.analizadorlexico.modelo.Lexema;

/**
 * Excepcion que se lanza cuando hay un error sintactico.
 *
 * @author alvar
 */
public class SintacticException extends RuntimeException {

    /**
     * Token actual del analisis.
     */
    private Lexema antes;
    /**
     * Lo que se esperaba
     */
    private String esperado;

    public SintacticException(Lexema antes, String esperado) {

        super("Simbolo no esperado: " + antes.getToken() + ". se esperaba " + esperado
        );

        this.antes = antes;
        this.esperado = esperado;
    }

    public Lexema getAntes() {
        return antes;
    }

    public void setAntes(Lexema antes) {
        this.antes = antes;
    }

    public String getEsperado() {
        return esperado;
    }

    public void setEsperado(String esperado) {
        this.esperado = esperado;
    }

}
