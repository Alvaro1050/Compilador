package co.edu.eam.tlf.analizadorsintactico.gramatica.implementaciones;

import co.edu.eam.tlf.analizadorsintactico.gramatica.definiciones.Gramatica;
import co.edu.eam.tlf.analizadorsintactico.sentencias.definicion.Sentencia;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Condicion;

public class GramaticaCondicion implements Gramatica {

    @Override
    public Condicion analizar(Sentencia padre, FlujoTokens flujoTokens) {
        // TODO Auto-generated method stub
        return new Condicion();
    }

}
