/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eam.tlf.analizadorsintactico.main;

import co.edu.eam.tlf.analizadorlexico.controlador.Analizador_lexico;
import co.edu.eam.tlf.analizadorlexico.modelo.Flujo_caracteres;
import co.edu.eam.tlf.analizadorsintactico.gramatica.implementaciones.FlujoTokens;
import co.edu.eam.tlf.analizadorlexico.modelo.Lexema;
import co.edu.eam.tlf.analizadorlexico.vista.FrmAnalizar;
import co.edu.eam.tlf.analizadorsintactico.gramatica.implementaciones.GramaticaClase;
import co.edu.eam.tlf.analizadorsintactico.sentencia.implementaciones.Clase;
import co.edu.eam.tlf.analizadorsintactico.sentencias.definicion.Sentencia;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author alvaro
 */
public class AnalizadorSintactico {

    /**
     * Analizador Lexico
     */
    private final Analizador_lexico analizadorLexico;

    private Sentencia raiz;

    public static String codigoJava;

    /**
     * Raiz del arbol de derivacion
     */
    private Clase unidadCompilacion;
    private char[] caracteres;

    List<Lexema> tokens;
    List<Lexema> errores;

    public List<Lexema> getTokens() {
        return tokens;
    }

    public void setTokens(List<Lexema> tokens) {
        this.tokens = tokens;
    }

    public List<Lexema> getErrores() {
        return errores;
    }

    public void setErrores(List<Lexema> errores) {
        this.errores = errores;
    }

    /**
     * Constrctor
     */
    public AnalizadorSintactico() {
        analizadorLexico = new Analizador_lexico();
    }

    /**
     * Metodo para analizar el codigo sintacticamente
     *
     * @param codigo
     */
    public void analizar(String codigo) throws IOException {
        caracteres = codigo.toCharArray();

        Flujo_caracteres fc = new Flujo_caracteres(0, caracteres);
        analizadorLexico.analizar(fc);
        tokens = analizadorLexico.getListaLexema();
        errores = analizadorLexico.getListaErrores();

        FrmAnalizar.listar();
        FrmAnalizar.listarErrores();
        //si no hay errores, se continua con el analisis semantico.
        if (errores.isEmpty()) {
            FlujoTokens flujo = new FlujoTokens(tokens);
            GramaticaClase gramm = new GramaticaClase();

            unidadCompilacion = gramm.analizar(null, flujo);

            System.out.println(unidadCompilacion.parse());
            String ruta = "C:\\Users\\alvar\\Documents\\NetBeansProjects\\Compilador\\src\\compilador.java";
            File archivo = new File(ruta);

            for (int i = 0; i < unidadCompilacion.getListaMetodos().getSentencias().size(); i++) {
                System.out.println(unidadCompilacion.getListaMetodos().getSentencias().get(i).getNombre().getToken());
                if (i == unidadCompilacion.getListaMetodos().getSentencias().size() - 1) {

                } else {
                    if (unidadCompilacion.getListaMetodos().getSentencias().get(i).getNombre().getToken().equals(unidadCompilacion.getListaMetodos().getSentencias().get(i + 1).getNombre().getToken())) {
                        System.out.println("HAY METODOS CON NOMBRES IGUALES: " + unidadCompilacion.getListaMetodos().getSentencias().get(i).getNombre().getToken());
                    }
                }
            }
            for (int i = 0; i < unidadCompilacion.getListaAtributos().getSentencias().size(); i++) {
                System.out.println(unidadCompilacion.getListaAtributos().getSentencias().get(i).getNombre().getToken());
                if (i == unidadCompilacion.getListaAtributos().getSentencias().size() - 1) {

                } else {
                    if (unidadCompilacion.getListaAtributos().getSentencias().get(i).getNombre().getToken().equals(unidadCompilacion.getListaAtributos().getSentencias().get(i + 1).getNombre().getToken())) {
                        System.out.println("HAY ATRIBUTOS CON NOMBRES IGUALES: " + unidadCompilacion.getListaAtributos().getSentencias().get(i).getNombre().getToken());
                    }
                }
            }
            BufferedWriter bw;
            if (archivo.exists()) {
                bw = new BufferedWriter(new FileWriter(archivo));
                bw.write(unidadCompilacion.parse());
            } else {
                bw = new BufferedWriter(new FileWriter(archivo));
                bw.write(unidadCompilacion.parse());
            }
            bw.close();
            raiz = getUnidadCompilacion();
            raiz.llenarHijos();

        }
    }

    public Sentencia getRaiz() {
        return raiz;
    }

    public void setRaiz(Sentencia raiz) {
        this.raiz = raiz;
    }

    public Analizador_lexico getAnalizadorLexico() {
        return analizadorLexico;
    }

    public Sentencia getUnidadCompilacion() {
        return unidadCompilacion;
    }

}
