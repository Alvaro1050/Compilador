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
public class Automata_OperadoresAritmeticos {

    int posInicial;
    int cont;
    boolean aceptada;/*para guardar los caratcteres y los va ir separando*/

    char[] car;

    public Lexema inicio(Flujo_caracteres flujo) {
        cont = flujo.getPosActual();
        posInicial = flujo.getPosActual();
        car = flujo.getCaracteres();
        aceptada = false;
        q0F();
        if (aceptada) {
            Analizador_lexico.flujo.setPosActual(cont);
            return new Lexema(car[posInicial] + "", "Operador aritmetico", cont, Analizador_lexico.fila);
        } else {
            return null;
        }
    }

    public void q0F() {
        if (cont < car.length) {/*cuantos espacios tiene mi arreglo*/

            if ((car[cont] == '+' || car[cont] == '-' || car[cont] == '/' || car[cont] == '*' || car[cont] == '%') && aceptada == false) {/*el arreglo car en el contador 0 lo vamos a comparar si es = a*/

                cont++;
                aceptada = true;
                q0F();

            } else if (car[cont] == ' ' || car[cont] == '\n') {
                validarEspacios();
            }
        }
    }

    public void validarEspacios() {
        if (car[cont] == ' ') {
            cont++;
            validarEspacios();
        } else if (car[cont] == '\n') {
            Analizador_lexico.fila++;
            cont++;
            validarEspacios();
        }
    }
}
