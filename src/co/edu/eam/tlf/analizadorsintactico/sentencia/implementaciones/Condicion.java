package co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones;

import java.util.ArrayList;
import java.util.List;

import co.edu.eam.tlf.analizadorsintactico.sentencias.definicion.Sentencia;

public class Condicion extends Sentencia {

    @Override
    public List<Sentencia> llenarHijos() {
        // TODO Auto-generated method stub
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "????? condicion ?????";
    }

    @Override
    public String parse() {
        // TODO Auto-generated method stub
        return " aqui irá una condicion ";
    }

}
