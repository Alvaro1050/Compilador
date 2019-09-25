package co.edu.eam.tlf.analizadorsintactico.gramatica.implementaciones;

import co.edu.eam.tlf.analizadorsintactico.excepciones.SintacticException;
import co.edu.eam.tlf.analizadorsintactico.gramatica.definiciones.Gramatica;
import co.edu.eam.tlf.analizadorsintactico.sentencias.definicion.Sentencia;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Condicion;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.IF;

public class GramaticaIF implements Gramatica {

    @Override
    public Sentencia analizar(Sentencia padre, FlujoTokens flujoTokens) {

        flujoTokens.getTokenActual();

        if (flujoTokens.getTokenActual().getToken().equals("if")) {
            return null;
        }
        flujoTokens.avanzar();

        if (flujoTokens.getTokenActual().getTipoLexema().equals("parentesis abierto")) {
            throw new SintacticException(flujoTokens.getTokenActual(), "parentesis abierto");
        }

        Condicion condicion = (Condicion) new GramaticaCondicion()
                .analizar(null, flujoTokens);

        if (condicion == null) {
            throw new SintacticException(flujoTokens.getTokenActual(), "parentesis abierto");
        }

        IF si = new IF();
        si.setCondicion(condicion);
        flujoTokens.avanzar();

        if (flujoTokens.getTokenActual().getTipoLexema().equals("parentesis cerrado")) {
            throw new SintacticException(flujoTokens.getTokenActual(), "parentesis cerrado");
        }

        return null;
    }

    /**
     * if (String){ }
     *
     * boolean a = x<10 && y>20
     *
     */
}
