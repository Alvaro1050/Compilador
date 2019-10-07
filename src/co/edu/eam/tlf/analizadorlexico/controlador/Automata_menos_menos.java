/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eam.tlf.analizadorlexico.controlador;

import co.edu.eam.tlf.analizadorlexico.modelo.Flujo_caracteres;
import co.edu.eam.tlf.analizadorlexico.modelo.Lexema;

/**
 *
 * @author alvar
 */
public class Automata_menos_menos {

    int posInicial;//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

    int cont;
    boolean aceptada;/*para guardar los caratcteres y los va ir separando*/

    char[] car;

    public Lexema inicio(Flujo_caracteres flujo) {
        cont = flujo.getPosActual();
        posInicial = flujo.getPosActual();//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

        car = flujo.getCaracteres();
        aceptada = false;
        q0();
        if (aceptada) {//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
            Analizador_lexico.flujo.setPosActual(cont);//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
            return new Lexema("--", "Operador aritmetico decremental", cont, Analizador_lexico.fila);//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
        } else {//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
            return null;//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
        }//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    }

    public void q0() {

        if (cont < car.length) {/*cuantos espacios tiene mi arreglo*/

            if (car[cont] == '-') {/*el arreglo car en el contador 0 lo vamos a comparar si es = a*/

                cont++;/*incrememnto mi contador*/

                qF();

            } else {
                Analizador_lexico.flujo.setPosActual(posInicial);//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

                aceptada = false;

            }
        }
    }

    public void qF() {

        if (cont < car.length) {/*cuantos espacios tiene mi arreglo*/

            if (car[cont] == '-' && aceptada == false) {/*el arreglo car en el contador 0 lo vamos a comparar si es = a*/

                cont++;/*incrememnto mi contador*/

                aceptada = true;
                qF();

            } else if (car[cont] == ' ' || car[cont] == '\n') {
                validarEspacios();
            }
        }
    }

    public void validarEspacios() {
        if (car[cont] == ' ' || car[cont] == '\n') {
            cont++;
            validarEspacios();
        } else if (car[cont] == '\n') {
            Analizador_lexico.fila++;
            cont++;
            validarEspacios();
        }
    }
}
