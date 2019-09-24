/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eam.tlf.analizadorlexico.modelo;

/**
 *
 * @author alvar
 */
public class Flujo_caracteres {

    int posActual;
    char[] caracteres;

    public Flujo_caracteres(int posActual, char[] caracteres) {
        this.posActual = posActual;
        this.caracteres = caracteres;
    }

    public int getPosActual() {
        return posActual;
    }

    public void setPosActual(int posActual) {
        this.posActual = posActual;
    }

    public char[] getCaracteres() {
        return caracteres;
    }

    public void setCaracteres(char[] caracteres) {
        this.caracteres = caracteres;
    }

}
