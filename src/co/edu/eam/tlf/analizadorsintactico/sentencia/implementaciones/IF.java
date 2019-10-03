package co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones;

import co.edu.eam.tlf.analizadorlexico.modelo.Lexema;
import java.util.List;

import co.edu.eam.tlf.analizadorsintactico.sentencias.definicion.Sentencia;
import java.util.ArrayList;

public class IF extends Sentencia {

    private ExpresionLogica condicion;

    private Lista<Sentencia> listaSentenciaSI;

    private Lista<Sentencia> listaSentenciaContrario;

    private IF contrario;

    public IF() {
        listaSentenciaContrario = new Lista<>();
        listaSentenciaSI = new Lista<>();
    }

    public IF(ExpresionLogica condicion, Lista<Sentencia> listaSentenciaSI, Lista<Sentencia> listaSentenciaContrario, IF contrario) {
        this.condicion = condicion;
        this.listaSentenciaSI = listaSentenciaSI;
        this.listaSentenciaContrario = listaSentenciaContrario;
        this.contrario = contrario;
    }

    public Lista<Sentencia> getListaSentenciaSI() {
        return listaSentenciaSI;
    }

    public void setListaSentenciaSI(Lista<Sentencia> listaSentenciaSI) {
        this.listaSentenciaSI = listaSentenciaSI;
    }

    public Lista<Sentencia> getListaSentenciaContrario() {
        return listaSentenciaContrario;
    }

    public void setListaSentenciaContrario(Lista<Sentencia> listaSentenciaContrario) {
        this.listaSentenciaContrario = listaSentenciaContrario;
    }

    public IF getContrario() {
        return contrario;
    }

    public void setContrario(IF contrario) {
        this.contrario = contrario;
    }
    
    
    

    @Override
    public List<Sentencia> llenarHijos() {
       
        hijos = new ArrayList<>();
        hijos.add(condicion);
        hijos.add(contrario);
        if (!listaSentenciaContrario.getSentencias().isEmpty()){
            hijos.add(listaSentenciaContrario);
        }
        if (!listaSentenciaSI.getSentencias().isEmpty()) {
            hijos.add(listaSentenciaSI);
            
        }
        return hijos;
    }

    

    @Override
    public String parse() {

        StringBuilder str = new StringBuilder();

        str.append("si").append("(");
        str.append(condicion.parse());
        str.append(")");
        str.append("{");

        for (Sentencia sentencia : listaSentenciaSI.getSentencias()) {
            str.append(sentencia.parse());
        }
        str.append("}");

        if (!listaSentenciaContrario.getSentencias().isEmpty()) {
            str.append("contrario").append("{");
            for (Sentencia sentencia : listaSentenciaContrario.getSentencias()) {
                str.append(sentencia.parse());
            }
            str.append("}");
        }

        return str.toString();
    }

    public void setCondicion(ExpresionLogica condicion) {
        this.condicion = condicion;
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
