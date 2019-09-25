package co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones;

import java.util.List;

import co.edu.eam.tlf.analizadorsintactico.sentencias.definicion.Sentencia;

public class IF extends Sentencia {

    private Condicion condicion;

    private List<Sentencia> bloqueThen;

    private List<Sentencia> bloqueElse;

    private IF elseif;

    @Override
    public List<Sentencia> llenarHijos() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String parse() {

        StringBuilder str = new StringBuilder();

        str.append("SI").append("{");
        str.append(condicion.parse());
        str.append("}");
        str.append("[");

        for (Sentencia sentencia : bloqueThen) {
            str.append(sentencia.parse());
        }
        str.append("]");

        if (!bloqueElse.isEmpty()) {
            str.append("SI NO").append("[");
            for (Sentencia sentencia : bloqueElse) {
                str.append(sentencia.parse());
            }
            str.append("]");
        }

        return str.toString();
    }

    public void setCondicion(Condicion condicion) {
        this.condicion = condicion;
    }

}
