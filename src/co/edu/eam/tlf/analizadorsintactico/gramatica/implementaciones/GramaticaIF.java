package co.edu.eam.tlf.analizadorsintactico.gramatica.implementaciones;

import co.edu.eam.tlf.analizadorlexico.modelo.Lexema;
import co.edu.eam.tlf.analizadorsintactico.gramatica.definiciones.Gramatica;
import co.edu.eam.tlf.analizadorsintactico.sentencias.definicion.Sentencia;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Condicion;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.IF;

public class GramaticaIF implements Gramatica {

    @Override
    public IF analizar(Sentencia raiz, FlujoTokens flujoTokens) {
        IF si = new IF();
        Lexema lexema = flujoTokens.getTokenActual();

        if (lexema.getToken().equals("si")) {
            lexema = flujoTokens.avanzar();
            if (lexema.getToken().equals("(")) {
                GramaticaCondicion gc = new GramaticaCondicion();
                lexema = flujoTokens.avanzar();

                Condicion condicion = gc.analizar(si, flujoTokens);

                if (condicion != null) {
                    si.setCondicion(condicion);
                    lexema = flujoTokens.avanzar();

                    if (lexema.getToken().equals(")")) {
                        lexema = flujoTokens.avanzar();

                        if (lexema.getToken().equals("{")) {
                            lexema = flujoTokens.avanzar();
                        }

                    }

                }

            }

        } else {
            flujoTokens.backTrack();
            return null;
        }
        return null;

    }
}
