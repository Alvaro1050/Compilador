/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eam.tlf.analizadorlexico.controlador;

import co.edu.eam.tlf.analizadorlexico.modelo.Flujo_caracteres;
import co.edu.eam.tlf.analizadorlexico.modelo.Lexema;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alvar
 */
public class Analizador_lexico {

    public static List<Lexema> listaLexema = new ArrayList<>();
    public static List<Lexema> listaErrores = new ArrayList<>();
    public static int fila = 1;
    Lexema lexe;
    public static Flujo_caracteres flujo = new Flujo_caracteres(0, null);
    int posInicial = 0;

    public void analizar(Flujo_caracteres flu) {
        flujo = flu;

        do {
            posInicial = flujo.getPosActual();
            automataVacio();
            automataFuncion();
            automataDividirNR();
            automataEntero();
            automataBoo();
            automataMesaje();
            automataNuevo();
            automataSumarNR();
            automataFlotante();
            automataHallarLimite();
            automataIntegrarFuncion();
            automataMultiplicarNR();
            automataCadena();
            automataRestarNR();
            automataMain();
            automataResolverVectores();
            automataRetornar();
            automataDefecto();
            automataClase();
            automataNada();
            automataRomper();
            automataCase();
            automataSi();
            automataCapturarDato();
            automataContrario();
            automataDecimal();
            automataContinuar();
            automataDerivarFuncion();
            automataMasMas();
            automataMenosMenos();
            automataOperadoresAsignacion();
            automataOperadoresRelacionales();
            automataOperadoresAritmeticos();
            automataCorcheteAbierto();
            automataCorcheteCerrado();
            automataLlaveAbierta();
            automataLlaveCerrado();
            automataParentesisAbierto();
            automataParentesisCerrado();
            automataComa();
            automataPunto();
            automataPuntoComa();
            automataIgual();
            automataDiples();
            automataOperadoresRelacionales();
            automataOperadoresLogicos();
            automataComilla();
            automataComentario();
            identificadores();
            errores();
        } while (flujo.getPosActual() < flujo.getCaracteres().length);
    }

    public void identificadores() {
        String identificador = "";
        if (flujo.getPosActual() == posInicial) {
            for (int i = flujo.getPosActual(); i < flujo.getCaracteres().length; i++) {
                if (Character.isLetter(flujo.getCaracteres()[i]) || Character.isDigit(flujo.getCaracteres()[i])) {
                    identificador = identificador + flujo.getCaracteres()[i];
                    if (i == flujo.getCaracteres().length - 1) {
                        i = validarEspacios(i);
                        flujo.setPosActual(i + 1);
                        Lexema lex = new Lexema(identificador, "Identificador");
                        listaLexema.add(lex);
                        break;
                    }
                } else {
                    i = validarEspacios(i);
                    flujo.setPosActual(i);
                    if (identificador == "") {

                    } else {
                        Lexema lex = new Lexema(identificador, "Identificador");
                        listaLexema.add(lex);
                    }
                    break;

                }
            }
        }
    }

    public void errores() {
        String error = "";
        if (flujo.getPosActual() == posInicial) {
            for (int i = flujo.getPosActual(); i < flujo.getCaracteres().length; i++) {
                if (flujo.getCaracteres()[i] == '_' || flujo.getCaracteres()[i] == '-'
                        || flujo.getCaracteres()[i] == '?' || flujo.getCaracteres()[i] == '$'
                        || flujo.getCaracteres()[i] == '¡' || flujo.getCaracteres()[i] == '¿'
                        || flujo.getCaracteres()[i] == ':' || flujo.getCaracteres()[i] == '°'
                        || flujo.getCaracteres()[i] == '^'
                        || flujo.getCaracteres()[i] == '"' || flujo.getCaracteres()[i] == '~'
                        || flujo.getCaracteres()[i] == '¬' || flujo.getCaracteres()[i] == '¨') {
                    error = error + flujo.getCaracteres()[i];
                    if (i == flujo.getCaracteres().length - 1) {
                        i = validarEspacios(i);
                        flujo.setPosActual(i + 1);
                        Lexema lex = new Lexema(error, "Error");
                        listaErrores.add(lex);
                        break;
                    }
                } else {
                    i = validarEspacios(i);
                    flujo.setPosActual(i);
                    if (error == "") {

                    } else {
                        Lexema lex = new Lexema(error, "Error");
                        listaErrores.add(lex);
                    }
                    break;
                }
            }
        }
    }

    public int validarEspacios(int pos) {
        if (flujo.getCaracteres()[pos] == ' ' || flujo.getCaracteres()[pos] == '\n') {
            pos++;
            validarEspacios(pos);
        }
        return pos;
    }

    public void automataPunto() {
        Automata_Punto atf = new Automata_Punto();
        lexe = atf.inicio(flujo);
        if (lexe != null) {
            listaLexema.add(lexe);
        }
    }

    public void automataNuevo() {
        Automata_nuevo atf = new Automata_nuevo();
        lexe = atf.inicio(flujo);
        if (lexe != null) {
            listaLexema.add(lexe);
        }
    }

    public void automataComentario() {
        Automata_comentario atf = new Automata_comentario();
        lexe = atf.inicio(flujo);
        if (lexe != null) {
            listaLexema.add(lexe);
        }
    }

    public void automataComilla() {
        Automata_comilla atf = new Automata_comilla();
        lexe = atf.inicio(flujo);
        if (lexe != null) {
            listaLexema.add(lexe);
        }
    }

    public void automataOperadoresLogicos() {
        Automata_OperadoresLogicos atf = new Automata_OperadoresLogicos();
        lexe = atf.inicio(flujo);
        if (lexe != null) {
            listaLexema.add(lexe);
        }
    }

    public void automataDiples() {
        Automata_diples atf = new Automata_diples();
        lexe = atf.inicio(flujo);
        if (lexe != null) {
            listaLexema.add(lexe);
        }
    }

    public void automataIgual() {
        Automata_igual atf = new Automata_igual();
        lexe = atf.inicio(flujo);
        if (lexe != null) {
            listaLexema.add(lexe);
        }
    }

    public void automataPuntoComa() {
        Automata_PuntoComa atf = new Automata_PuntoComa();
        lexe = atf.inicio(flujo);
        if (lexe != null) {
            listaLexema.add(lexe);
        }
    }

    public void automataComa() {
        Automata_Coma atf = new Automata_Coma();
        lexe = atf.inicio(flujo);
        if (lexe != null) {
            listaLexema.add(lexe);
        }
    }

    public void automataParentesisAbierto() {
        Automata_parentesis_abierto atf = new Automata_parentesis_abierto();
        lexe = atf.inicio(flujo);
        if (lexe != null) {
            listaLexema.add(lexe);
        }
    }

    public void automataParentesisCerrado() {
        Automata_parentesis_cerrado atf = new Automata_parentesis_cerrado();
        lexe = atf.inicio(flujo);
        if (lexe != null) {
            listaLexema.add(lexe);
        }
    }

    public void automataLlaveAbierta() {
        Automata_llave_abierta atf = new Automata_llave_abierta();
        lexe = atf.inicio(flujo);
        if (lexe != null) {
            listaLexema.add(lexe);
        }
    }

    public void automataLlaveCerrado() {
        Automata_llave_cerrado atf = new Automata_llave_cerrado();
        lexe = atf.inicio(flujo);
        if (lexe != null) {
            listaLexema.add(lexe);
        }
    }

    public void automataCorcheteAbierto() {
        Automata_corchete_abierto atf = new Automata_corchete_abierto();
        lexe = atf.inicio(flujo);
        if (lexe != null) {
            listaLexema.add(lexe);
        }
    }

    public void automataCorcheteCerrado() {
        Automata_corchete_cerrado atf = new Automata_corchete_cerrado();
        lexe = atf.inicio(flujo);
        if (lexe != null) {
            listaLexema.add(lexe);
        }
    }

    public void automataMenosMenos() {
        Automata_menos_menos atf = new Automata_menos_menos();
        lexe = atf.inicio(flujo);
        if (lexe != null) {
            listaLexema.add(lexe);
        }
    }

    public void automataOperadoresAritmeticos() {
        Automata_OperadoresAritmeticos atf = new Automata_OperadoresAritmeticos();
        lexe = atf.inicio(flujo);
        if (lexe != null) {
            listaLexema.add(lexe);
        }
    }

    public void automataOperadoresRelacionales() {
        Automata_OperadoresRelaciones atf = new Automata_OperadoresRelaciones();
        lexe = atf.inicio(flujo);
        if (lexe != null) {
            listaLexema.add(lexe);
        }
    }

    public void automataOperadoresAsignacion() {
        Automata_OperadoresAsignacion atf = new Automata_OperadoresAsignacion();
        lexe = atf.inicio(flujo);
        if (lexe != null) {
            listaLexema.add(lexe);
        }
    }

    public void automataMasMas() {
        Automata_mas_mas atf = new Automata_mas_mas();
        lexe = atf.inicio(flujo);
        if (lexe != null) {
            listaLexema.add(lexe);
        }
    }

    public void automataDerivarFuncion() {
        Automata_derivarFuncion atf = new Automata_derivarFuncion();
        lexe = atf.inicio(flujo);
        if (lexe != null) {
            listaLexema.add(lexe);
        }
    }

    public void automataDefecto() {
        Automata_defecto atf = new Automata_defecto();
        lexe = atf.inicio(flujo);
        if (lexe != null) {
            listaLexema.add(lexe);
        }
    }

    public void automataDecimal() {
        Automata_decimal atf = new Automata_decimal();
        lexe = atf.inicio(flujo);
        if (lexe != null) {
            listaLexema.add(lexe);
        }
    }

    public void automataContrario() {
        Automata_contrario atf = new Automata_contrario();
        lexe = atf.inicio(flujo);
        if (lexe != null) {
            listaLexema.add(lexe);
        }
    }

    public void automataContinuar() {
        Automata_continuar atf = new Automata_continuar();
        lexe = atf.inicio(flujo);
        if (lexe != null) {
            listaLexema.add(lexe);
        }
    }

    public void automataCase() {
        Automata_case atf = new Automata_case();
        lexe = atf.inicio(flujo);
        if (lexe != null) {
            listaLexema.add(lexe);
        }
    }

    public void automataRomper() {
        Automata_romper atf = new Automata_romper();
        lexe = atf.inicio(flujo);
        if (lexe != null) {
            listaLexema.add(lexe);
        }
    }

    public void automataRetornar() {
        Automata_retornar atf = new Automata_retornar();
        lexe = atf.inicio(flujo);
        if (lexe != null) {
            listaLexema.add(lexe);
        }
    }

    public void automataRestarNR() {
        Automata_restarNR atf = new Automata_restarNR();
        lexe = atf.inicio(flujo);
        if (lexe != null) {
            listaLexema.add(lexe);
        }
    }

    public void automataResolverVectores() {
        Automata_resolverVectores atf = new Automata_resolverVectores();
        lexe = atf.inicio(flujo);
        if (lexe != null) {
            listaLexema.add(lexe);
        }
    }

    public void automataNada() {
        Automata_nada atf = new Automata_nada();
        lexe = atf.inicio(flujo);
        if (lexe != null) {
            listaLexema.add(lexe);
        }
    }

    public void automataMultiplicarNR() {
        Automata_multiplicarNR atf = new Automata_multiplicarNR();
        lexe = atf.inicio(flujo);
        if (lexe != null) {
            listaLexema.add(lexe);
        }
    }

    public void automataMain() {
        Automata_main atf = new Automata_main();
        lexe = atf.inicio(flujo);
        if (lexe != null) {
            listaLexema.add(lexe);
        }
    }

    public void automataIntegrarFuncion() {
        Automata_integrarFuncion atf = new Automata_integrarFuncion();
        lexe = atf.inicio(flujo);
        if (lexe != null) {
            listaLexema.add(lexe);
        }
    }

    public void automataHallarLimite() {
        Automata_hallarLimite atf = new Automata_hallarLimite();
        lexe = atf.inicio(flujo);
        if (lexe != null) {
            listaLexema.add(lexe);
        }
    }

    public void automataFlotante() {
        Automata_flotante atf = new Automata_flotante();
        lexe = atf.inicio(flujo);
        if (lexe != null) {
            listaLexema.add(lexe);
        }
    }

    public void automataEntero() {
        Automata_entero atf = new Automata_entero();
        lexe = atf.inicio(flujo);
        if (lexe != null) {
            listaLexema.add(lexe);
        }
    }

    public void automataDividirNR() {
        Automata_dividirNR atf = new Automata_dividirNR();
        lexe = atf.inicio(flujo);
        if (lexe != null) {
            listaLexema.add(lexe);
        }
    }

    public void automataCadena() {
        Automata_cadena atf = new Automata_cadena();
        lexe = atf.inicio(flujo);
        if (lexe != null) {
            listaLexema.add(lexe);
        }
    }

    public void automataBoo() {
        Automata_boo atf = new Automata_boo();
        lexe = atf.inicio(flujo);
        if (lexe != null) {
            listaLexema.add(lexe);
        }
    }

    public void automataFuncion() {
        Automata_Funcion atf = new Automata_Funcion();
        lexe = atf.inicio(flujo);
        if (lexe != null) {
            listaLexema.add(lexe);
        }
    }

    public void automataSumarNR() {
        Automata_SumarNR atf = new Automata_SumarNR();
        lexe = atf.inicio(flujo);
        if (lexe != null) {
            listaLexema.add(lexe);
        }
    }

    public void automataSi() {
        Automata_si atf = new Automata_si();
        lexe = atf.inicio(flujo);
        if (lexe != null) {
            listaLexema.add(lexe);
        }
    }

    public void automataMesaje() {
        Automata_Mensaje atf = new Automata_Mensaje();
        lexe = atf.inicio(flujo);
        if (lexe != null) {
            listaLexema.add(lexe);
        }
    }

    public void automataCapturarDato() {
        Automata_CapturarDato atf = new Automata_CapturarDato();
        lexe = atf.inicio(flujo);
        if (lexe != null) {
            listaLexema.add(lexe);
        }
    }

    public void automataClase() {
        Automata_Clase atf = new Automata_Clase();
        lexe = atf.inicio(flujo);
        if (lexe != null) {
            listaLexema.add(lexe);
        }
    }

    public void automataVacio() {
        Automata_vacio atf = new Automata_vacio();
        lexe = atf.inicio(flujo);
        if (lexe != null) {
            listaLexema.add(lexe);
        }
    }

    public List<Lexema> getListaLexema() {
        return listaLexema;
    }

    public void setListaLexema(List<Lexema> listaLexema) {
        this.listaLexema = listaLexema;
    }

    public List<Lexema> getListaErrores() {
        return listaErrores;
    }

    public void setListaErrores(List<Lexema> listaErrores) {
        Analizador_lexico.listaErrores = listaErrores;
    }

}
