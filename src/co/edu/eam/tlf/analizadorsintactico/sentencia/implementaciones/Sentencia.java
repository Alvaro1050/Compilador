/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones;

import co.edu.eam.tlf.analizadorlexico.modelo.Lexema;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author alvar
 */
public class Sentencia extends co.edu.eam.tlf.analizadorsintactico.sentencias.definicion.Sentencia {

    private DeclaradorVariable declaradorVariable;
    private Lexema identificador;
    private Expresion expresion;
    private Expresion expresion2;
    private Lexema comillaAbierta;
    private Lexema mensaje;
    private Lexema into;
    private Lexema comillaCerrada;
    private IF si;
    private Para para;
    private Lexema identificadorRomper;

    public Sentencia() {
    }

    public DeclaradorVariable getDeclaradorVariable() {
        return declaradorVariable;
    }

    public Lexema getComillaAbierta() {
        return comillaAbierta;
    }

    public void setComillaAbierta(Lexema comillaAbierta) {
        this.comillaAbierta = comillaAbierta;
    }

    public Expresion getExpresion2() {
        return expresion2;
    }

    public void setExpresion2(Expresion expresion2) {
        this.expresion2 = expresion2;
    }

    public Lexema getComillaCerrada() {
        return comillaCerrada;
    }

    public Lexema getMensaje() {
        return mensaje;
    }

    public void setMensaje(Lexema mensaje) {
        this.mensaje = mensaje;
    }

    public Lexema getInto() {
        return into;
    }

    public void setInto(Lexema into) {
        this.into = into;
    }

    public void setComillaCerrada(Lexema comillaCerrada) {
        this.comillaCerrada = comillaCerrada;
    }

    public void setDeclaradorVariable(DeclaradorVariable declaradorVariable) {
        this.declaradorVariable = declaradorVariable;
    }

    public Lexema getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Lexema identificador) {
        this.identificador = identificador;
    }

    public Expresion getExpresion() {
        return expresion;
    }

    public void setExpresion(Expresion expresion) {
        this.expresion = expresion;
    }

    public IF getSi() {
        return si;
    }

    public void setSi(IF si) {
        this.si = si;
    }

    public Para getPara() {
        return para;
    }

    public Lexema getIdentificadorRomper() {
        return identificadorRomper;
    }

    public void setIdentificadorRomper(Lexema identificadorRomper) {
        this.identificadorRomper = identificadorRomper;
    }

    public void setPara(Para para) {
        this.para = para;
    }

    @Override
    public List<co.edu.eam.tlf.analizadorsintactico.sentencias.definicion.Sentencia> llenarHijos() {
        hijos = new ArrayList<>();

        if (declaradorVariable != null) {
            hijos.add(declaradorVariable);
        }
        if (si != null) {
            hijos.add(si);
        }
        if (para != null) {
            hijos.add(para);
        }
        if (expresion != null) {
            hijos.add(expresion);
        }
        return hijos;
    }

    @Override
    public String toString() {
        return "Sentencia: ";
    }

    @Override
    public String parse() {
        StringBuilder str = new StringBuilder();

        if (declaradorVariable != null) {
            str.append(declaradorVariable.parse());
        }
        if (identificadorRomper != null) {
            str.append(identificadorRomper.getToken()).append(" ");
            str.append(identificador.getToken());
        }

        if (into != null) {
            str.append("JOptionPane.showInputDialog").append("(this,");

            if (identificador != null) {
                String limpio;
                limpio = identificador.getToken().substring(1, identificador.getToken().length() - 1);
                str.append("\"").append(limpio).append("\"");
            }
            str.append(");");
        }

        if (mensaje != null) {
            str.append("System.out.println");
            str.append("(");

            if (expresion2 != null) {
                str.append(expresion2.parse());
            }

            str.append("); ");

        }

        if (si != null) {
            str.append(si.parse());
        }
        if (para != null) {
            str.append(para.parse());
        }
        if (expresion != null) {
            str.append("return ");
            str.append(expresion.parse());
        }
        return str.toString();
    }

}
