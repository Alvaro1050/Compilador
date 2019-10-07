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
 * @author Lenovo
 */
public class Automata_derivarFuncion {

    int posInicial;
    int cont;
    boolean aceptada;/*para guardar los caratcteres y los va ir separando*/

    char[] car;

    public Lexema inicio(Flujo_caracteres flujo) {
        cont = flujo.getPosActual();
        posInicial = flujo.getPosActual();
        car = flujo.getCaracteres();
        aceptada = false;
        q0();

        if (aceptada) {
            Analizador_lexico.flujo.setPosActual(cont);

            return new Lexema("derivarFuncion", "Palabra Reservada", cont, Analizador_lexico.fila);
        } else {
            return null;
        }
    }

    public void q0() {

        if (cont < car.length) {/*cuantos espacios tiene mi arreglo*/

            if (car[cont] == 'd') {/*el arreglo car en el contador 0 lo vamos a comparar si es = a*/

                cont++;/*incrememnto mi contador*/

                q1();

            } else {
                Analizador_lexico.flujo.setPosActual(posInicial);

                aceptada = false;

            }
        }
    }

    public void q1() {

        if (cont < car.length) {/*cuantos espacios tiene mi arreglo*/

            if (car[cont] == 'e') {/*el arreglo car en el contador 0 lo vamos a comparar si es = a*/

                cont++;/*incrememnto mi contador*/

                q2();

            } else {
                Analizador_lexico.flujo.setPosActual(posInicial);

                aceptada = false;

            }
        }
    }

    public void q2() {

        if (cont < car.length) {/*cuantos espacios tiene mi arreglo*/

            if (car[cont] == 'r') {/*el arreglo car en el contador 0 lo vamos a comparar si es = a*/

                cont++;/*incrememnto mi contador*/

                q3();

            } else {
                Analizador_lexico.flujo.setPosActual(posInicial);

                aceptada = false;

            }
        }
    }

    public void q3() {

        if (cont < car.length) {/*cuantos espacios tiene mi arreglo*/

            if (car[cont] == 'i') {/*el arreglo car en el contador 0 lo vamos a comparar si es = a*/

                cont++;/*incrememnto mi contador*/

                q4();

            } else {
                Analizador_lexico.flujo.setPosActual(posInicial);

                aceptada = false;

            }
        }
    }

    public void q4() {

        if (cont < car.length) {/*cuantos espacios tiene mi arreglo*/

            if (car[cont] == 'v') {/*el arreglo car en el contador 0 lo vamos a comparar si es = a*/

                cont++;/*incrememnto mi contador*/

                q5();

            } else {
                Analizador_lexico.flujo.setPosActual(posInicial);

                aceptada = false;

            }
        }
    }

    public void q5() {

        if (cont < car.length) {/*cuantos espacios tiene mi arreglo*/

            if (car[cont] == 'a') {/*el arreglo car en el contador 0 lo vamos a comparar si es = a*/

                cont++;/*incrememnto mi contador*/

                q6();

            } else {
                Analizador_lexico.flujo.setPosActual(posInicial);

                aceptada = false;

            }
        }
    }

    public void q6() {

        if (cont < car.length) {/*cuantos espacios tiene mi arreglo*/

            if (car[cont] == 'r') {/*el arreglo car en el contador 0 lo vamos a comparar si es = a*/

                cont++;/*incrememnto mi contador*/

                q7();

            } else {
                Analizador_lexico.flujo.setPosActual(posInicial);

                aceptada = false;

            }
        }
    }

    public void q7() {

        if (cont < car.length) {/*cuantos espacios tiene mi arreglo*/

            if (car[cont] == 'F') {/*el arreglo car en el contador 0 lo vamos a comparar si es = a*/

                cont++;/*incrememnto mi contador*/

                q8();

            } else {
                Analizador_lexico.flujo.setPosActual(posInicial);

                aceptada = false;

            }
        }
    }

    public void q8() {

        if (cont < car.length) {/*cuantos espacios tiene mi arreglo*/

            if (car[cont] == 'u') {/*el arreglo car en el contador 0 lo vamos a comparar si es = a*/

                cont++;/*incrememnto mi contador*/

                q9();

            } else {
                Analizador_lexico.flujo.setPosActual(posInicial);

                aceptada = false;

            }
        }
    }

    public void q9() {

        if (cont < car.length) {/*cuantos espacios tiene mi arreglo*/

            if (car[cont] == 'n') {/*el arreglo car en el contador 0 lo vamos a comparar si es = a*/

                cont++;/*incrememnto mi contador*/

                q10();

            } else {
                Analizador_lexico.flujo.setPosActual(posInicial);

                aceptada = false;

            }
        }
    }

    public void q10() {

        if (cont < car.length) {/*cuantos espacios tiene mi arreglo*/

            if (car[cont] == 'c') {/*el arreglo car en el contador 0 lo vamos a comparar si es = a*/

                cont++;/*incrememnto mi contador*/

                q11();

            } else {
                Analizador_lexico.flujo.setPosActual(posInicial);

                aceptada = false;

            }
        }
    }

    public void q11() {

        if (cont < car.length) {/*cuantos espacios tiene mi arreglo*/

            if (car[cont] == 'i') {/*el arreglo car en el contador 0 lo vamos a comparar si es = a*/

                cont++;/*incrememnto mi contador*/

                q12();

            } else {
                Analizador_lexico.flujo.setPosActual(posInicial);

                aceptada = false;

            }
        }
    }

    public void q12() {
        if (cont < car.length) {/*cuantos espacios tiene mi arreglo*/

            if (car[cont] == 'o') {/*el arreglo car en el contador 0 lo vamos a comparar si es = a*/

                cont++;/*incrememnto mi contador*/

                qF();

            } else {
                Analizador_lexico.flujo.setPosActual(posInicial);

                aceptada = false;

            }
        }
    }

    public void qF() {
        if (cont < car.length) {/*cuantos espacios tiene mi arreglo*/

            if (car[cont] == 'n' && aceptada == false) {/*el arreglo car en el contador 0 lo vamos a comparar si es = a*/

                aceptada = true;
                cont++;
                qF();

            } else if (Character.isLetter(car[cont]) || Character.isDigit(car[cont])) {
                Analizador_lexico.flujo.setPosActual(posInicial);

                aceptada = false;
                cont--;

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
