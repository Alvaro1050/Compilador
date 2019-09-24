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

    public Lexema(String token, String tipoLexema) {
        this.token = token;
        this.tipoLexema = tipoLexema;
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
