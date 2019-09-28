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
public class Tipo extends Sentencia {

    private Lexema tipoIdentificador;

    public Tipo(Lexema tipoIdentificador) {
        this.tipoIdentificador = tipoIdentificador;
    }
    
    public Tipo (){
        
    }

    public Lexema getTipoIdentificador() {
        return tipoIdentificador;
    }

    public void setTipoIdentificador(Lexema tipoIdentificador) {
        this.tipoIdentificador = tipoIdentificador;
    }

    
    
    @Override
    public List<Sentencia> llenarHijos() {
        hijos = new ArrayList<>();

        hijos.add(new SentenciaToken(tipoIdentificador));
        
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