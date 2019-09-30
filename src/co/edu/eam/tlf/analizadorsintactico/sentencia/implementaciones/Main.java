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

    private Lexema vacio;
    private Lexema main;
    private BloqueSentencia bloqueSentencia;

    public Main(Lexema vacio, Lexema main, BloqueSentencia bloqueSentencia) {
        this.vacio = vacio;
        this.main = main;
        this.bloqueSentencia = bloqueSentencia;
    }

    public Main() {
        
    }

    public Lexema getVacio() {
        return vacio;
    }

    public void setVacio(Lexema vacio) {
        this.vacio = vacio;
    }

    public Lexema getMain() {
        return main;
    }

    public void setMain(Lexema main) {
        this.main = main;
    }

    public BloqueSentencia getBloqueSentencia() {
        return bloqueSentencia;
    }

    public void setBloqueSentencia(BloqueSentencia bloqueSentencia) {
        this.bloqueSentencia = bloqueSentencia;
    }
    
    
    
    
    
    
    @Override
    public List<Sentencia> llenarHijos() {

        
        hijos = new ArrayList<>();

        hijos.add(new SentenciaToken(main));
        hijos.add(new SentenciaToken(vacio));
        hijos.add(bloqueSentencia);

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
