/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;

import analizadores.AnalisadorLexico;
import static analizadores.AnalisadorLexico.preencherArrayPalavrasReservadas;
import static analizadores.AnalisadorLexico.i;
import analizadores.AnalisadorSintatico;
import views.Main;

/**
 *
 * @author Dell-User
 */
public class Compiler {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        //Palavras resevadas
        preencherArrayPalavrasReservadas();

        try {

            //new Main().setVisible(true);
            AnalisadorLexico sc = new AnalisadorLexico("./input.txt");

            AnalisadorSintatico anaS = new AnalisadorSintatico(sc);

        } catch (Exception ex) {
        }
        i = 0;
    }

}













 /*AnalisadorLexico sc = new AnalisadorLexico("./input.txt");

            Out token = new Out();

            
            do {
                token = sc.nextToken();
                if (token.getToken() != Token.TOKEN_EOF) {
                    System.out.println("Token: " + token.getToken() + " , Lexema: " + token.getLexema() + " , Linha: " + token.getLinha());

                }
            } while (token.getToken() != Token.TOKEN_EOF);*/
