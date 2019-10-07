/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eam.tlf.analizadorlexico.modelo;

/**
 *
 * @author alvar
 */
public class Lexema {

    String token;
    String tipoLexema;
    int columna;
    int fila;

    public Lexema(String token, String tipoLexema) {
        this.token = token;
        this.tipoLexema = tipoLexema;
    }

    public Lexema(String token, String tipoLexema, int columna, int fila) {
        this.token = token;
        this.tipoLexema = tipoLexema;
        this.columna = columna;
        this.fila = fila;
    }
    
    

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }
    
    

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTipoLexema() {
        return tipoLexema;
    }

    public void setTipoLexema(String tipoLexema) {
        this.tipoLexema = tipoLexema;
    }

}
