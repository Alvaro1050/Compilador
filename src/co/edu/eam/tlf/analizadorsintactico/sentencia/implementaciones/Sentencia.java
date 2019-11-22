/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones;

import co.edu.eam.tlf.analizadorlexico.modelo.Lexema;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alvar
 */
public class Sentencia extends co.edu.eam.tlf.analizadorsintactico.sentencias.definicion.Sentencia {

    private DeclaradorVariable declaradorVariable;
    private Lexema identificador;
    private Expresion expresion;
    private IF si;
    private Para para;
    private Lexema identificadorRomper;

    public Sentencia() {
    }

    public DeclaradorVariable getDeclaradorVariable() {
        return declaradorVariable;
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
