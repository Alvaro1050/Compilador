/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones;

import co.edu.eam.tlf.analizadorlexico.modelo.Lexema;
import co.edu.eam.tlf.analizadorsintactico.sentencias.definicion.Sentencia;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class Main extends Sentencia {

    private Lexema retorno;
    private Lexema main;
    private Lexema tipoIdentidicador;
    private Lexema nombreParametro;
    private Lista<Sentencia> listaSentencia;

    public Main() {
        listaSentencia = new Lista<>();
    }

    public Lexema getRetorno() {
        return retorno;
    }

    public void setRetorno(Lexema retorno) {
        this.retorno = retorno;
    }

    public Lexema getMain() {
        return main;
    }

    public void setMain(Lexema main) {
        this.main = main;
    }

    public Lexema getTipoIdentidicador() {
        return tipoIdentidicador;
    }

    public void setTipoIdentidicador(Lexema tipoIdentidicador) {
        this.tipoIdentidicador = tipoIdentidicador;
    }

    public Lexema getNombreParametro() {
        return nombreParametro;
    }

    public void setNombreParametro(Lexema nombreParametro) {
        this.nombreParametro = nombreParametro;
    }

    public Lista<Sentencia> getListaSentencia() {
        return listaSentencia;
    }

    public void setListaSentencia(Lista<Sentencia> listaSentencia) {
        this.listaSentencia = listaSentencia;
    }

    @Override
    public List<Sentencia> llenarHijos() {

        hijos = new ArrayList<>();

        hijos.add(new SentenciaToken(main));
        hijos.add(new SentenciaToken(retorno));

        return hijos;
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String parse() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}