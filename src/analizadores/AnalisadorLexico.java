/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import tokens.Out;
import tokens.Token;
import static views.Main.txtSource;

/**
 *
 * @author Dell-User
 */
public class AnalisadorLexico {
    //Vetor de caracteres porque vai se ler o arquivo inteiro

    private char[] content;
    //Variavel que vai trocar os estados
    private int estado;
    //Variavel que vai determinar a posicação dos caracteres no Array de char
    private int pos;
    //Linha
    public static int i = 1;

    //Recepção e leitura do ficheiro
    public AnalisadorLexico(String filename) {
        /*Aqui le-se o arquivo e colocar o arquivo num vetor de caracteres
        e depois gerar uma String em função disso 
         */
        try {
            //String que vai receber o conteudo vindo do ficheiro
            String txtConteudo;
            //Ler tudo do arquivo/Ficheiro
            txtConteudo = new String(Files.readAllBytes(Paths.get(filename)), StandardCharsets.UTF_8);
            //txtConteudo=txtSource.getText();
            //Fazendo Debug no Programma
            System.out.println("------ Arquivo Original ------");
            System.out.println(txtConteudo);
            System.out.println("------------------------------");
            //Colocar cada palavra no vetor de char que foi declarado
            content = txtConteudo.toCharArray();
            //Inicializar a posição com 0
            pos = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Função que vai permitir voltar
    private void voltar() {
        pos--;
    }

    //Função pra verificar se é digito ou não (Se for um digito vai retornar verdadeiro se estiver nesse intervalo)
    private boolean isDigito(char caracter) {
        return caracter >= '0' && caracter <= '9';
    }

    //Função pra verificar se é caracter ou não (Se for um caracter vai retornar verdadeiro se estiver nesse intervalo)
    private boolean isChar(char caracter) {
        return String.valueOf(caracter).matches(identifiers);
    }
    //Expressão regular para validar variaveis
    protected String identifiers = "([a-z]|[A-Z])([a-z]+|[A-Z])*";

    //Função que vai pegar o proximo caracter
    private char nextChar() {
        return content[pos++];
    }

    //Perguntar se é o fim do arquivo (se for retorna true, senao retorna false)
    private boolean isAndOfFile() {
        return pos == content.length;
    }

    //Array de Palavras palavras Reservadas
    public static final List palavrasReservadas = new ArrayList();

    //Prencher o array de Palavras palavrasReservadas
    public static void preencherArrayPalavrasReservadas() {
        palavrasReservadas.add("stdlib");
        palavrasReservadas.add("stdio");
        palavrasReservadas.add("void");
        palavrasReservadas.add("int");
        palavrasReservadas.add("char");
        palavrasReservadas.add("float");
        palavrasReservadas.add("long");
        palavrasReservadas.add("double");
        palavrasReservadas.add("_Bool");
        palavrasReservadas.add("struct");
        palavrasReservadas.add("typedef");
        palavrasReservadas.add("if");
        palavrasReservadas.add("else");
        palavrasReservadas.add("while");
        palavrasReservadas.add("do");
        palavrasReservadas.add("for");
        palavrasReservadas.add("return");
        palavrasReservadas.add("break");
        palavrasReservadas.add("switch");
        palavrasReservadas.add("case");
        palavrasReservadas.add("continue");
        palavrasReservadas.add("printf");
        palavrasReservadas.add("#define");
        palavrasReservadas.add("#include");
    }

    public Out nextToken() {
        //Variavel que vai receber o proximo caracter (que será o actual)
        char caracterActual;
        //String pra receber o termo (a palavra a ser mostrada no final da execução)
        String palavra = "";
        //Instanciando a Ojecto de Saida
        Out saida;
        //Se for o fim do arquivo retorna null
        if (isAndOfFile()) {
            saida = new Out();
            saida.setLexema(palavra);
            saida.setToken(Token.TOKEN_EOF);
            saida.setLinha(i);
            return saida;
        }
        //Definir o estado inicial como 0
        estado = 0;
        //Ciclo pra percorrer o automato completo
        while (true) {

            //Recebendo o proximo caractrer
            caracterActual = nextChar();
            //Escolhendo os estados
            switch (estado) {
                case 0:
                    if (caracterActual == ']') {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //Voltar ao estado 0
                        estado = 0;
                        //Instanciar e retornando os valores 
                        saida = new Out();
                        saida.setLexema(palavra);
                        saida.setToken(Token.TOKEN_FECHA_PARENTESES_RECTOS);
                        saida.setLinha(i);
                        return saida;
                    } else if (caracterActual == '[') {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //Voltar ao estado 0
                        estado = 0;
                        //Instanciar e retornando os valores 
                        saida = new Out();
                        saida.setLexema(palavra);
                        saida.setToken(Token.TOKEN_ABRE_PARENTESES_RECTOS);
                        saida.setLinha(i);
                        return saida;
                    } else if (caracterActual == ')') {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //Voltar ao estado 0
                        estado = 0;
                        //Instanciar e retornando os valores 
                        saida = new Out();
                        saida.setLexema(palavra);
                        saida.setToken(Token.TOKEN_FECHA_PARENTESES);
                        saida.setLinha(i);
                        return saida;
                    } else if (caracterActual == '(') {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //Voltar ao estado 0
                        estado = 0;
                        //Instanciar e retornando os valores 
                        saida = new Out();
                        saida.setLexema(palavra);
                        saida.setToken(Token.TOKEN_ABRE_PARENTESES);
                        saida.setLinha(i);
                        return saida;
                    } else if (caracterActual == '{') {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //Voltar ao estado 0
                        estado = 0;
                        //Instanciar e retornando os valores 
                        saida = new Out();
                        saida.setLexema(palavra);
                        saida.setToken(Token.TOKEN_ABRE_CHAVETAS);
                        saida.setLinha(i);
                        return saida;
                    } else if (caracterActual == '}') {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //Voltar ao estado 0
                        estado = 0;
                        //Instanciar e retornando os valores 
                        saida = new Out();
                        saida.setLexema(palavra);
                        saida.setToken(Token.TOKEN_FECHA_CHAVETAS);
                        saida.setLinha(i);
                        return saida;
                    } else if (caracterActual == ';') {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //Voltar ao estado 0
                        estado = 0;
                        //Instanciar e retornando os valores 
                        saida = new Out();
                        saida.setLexema(palavra);
                        saida.setToken(Token.TOKEN_PONTO_E_VIRGULA);
                        saida.setLinha(i);
                        return saida;
                    } else if (caracterActual == ',') {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //Voltar ao estado 0
                        estado = 0;
                        //Instanciar e retornando os valores 
                        saida = new Out();
                        saida.setLexema(palavra);
                        saida.setToken(Token.TOKEN_VIRGULA);
                        saida.setLinha(i);
                        return saida;
                    } else if (caracterActual == '.') {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //Voltar ao estado 0
                        estado = 0;
                        //Instanciar e retornando os valores 
                        saida = new Out();
                        saida.setLexema(palavra);
                        saida.setToken(Token.TOKEN_PONTO);
                        saida.setLinha(i);
                        return saida;
                    } else if (caracterActual == '~') {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //Voltar ao estado 0
                        estado = 0;
                        //Instanciar e retornando os valores 
                        saida = new Out();
                        saida.setLexema(palavra);
                        saida.setToken(Token.TOKEN_TIL);
                        saida.setLinha(i);
                        return saida;
                    } else if (caracterActual == '\n') {
                        //Voltar ao estado 0
                        estado = 0;
                        i++;
                    } else if (caracterActual == '\n') {
                        //Voltar ao estado 0
                        estado = 0;
                        i++;
                    } else if (caracterActual == '\t' || caracterActual == '\r' || caracterActual == ' ') {
                        //Voltar ao estado 0
                        estado = 0;
                    } else if (caracterActual == '/') {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //Vai para o estado 7
                        estado = 7;
                    }//Se for uma letra vai para o estado 
                    else if (isChar(caracterActual) || caracterActual == '_' || caracterActual == '#') {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //vai pra o estado 15
                        estado = 15;
                    } else if (isDigito(caracterActual)) {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //vai pra o estado 17
                        estado = 17;
                    } else if (caracterActual == '"') {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //vai pra o estado 70
                        estado = 70;
                    } else if (caracterActual == '!') {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //vai pra o estado 22
                        estado = 22;
                    } else if (caracterActual == '^') {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //vai pra o estado 25
                        estado = 25;
                    } else if (caracterActual == '\'') {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //vai pra o estado 28
                        estado = 28;
                    } else if (caracterActual == '=') {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //vai pra o estado 72
                        estado = 72;
                    } else if (caracterActual == '*') {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //vai pra o estado 49
                        estado = 49;
                    } else if (caracterActual == '-') {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //vai pra o estado 41
                        estado = 41;
                    } else if (caracterActual == '%') {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //vai pra o estado 41
                        estado = 46;
                    } else if (caracterActual == '&') {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //vai pra o estado 58
                        estado = 58;
                    } else if (caracterActual == '|') {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //vai pra o estado 62
                        estado = 62;
                    } else if (caracterActual == '+') {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //vai pra o estado 37
                        estado = 37;
                    } else if (caracterActual == '<') {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //vai pra o estado 31
                        estado = 31;
                    } else if (caracterActual == '>') {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //vai para o estado 52
                        estado = 52;
                    }
                    break;

                case 7:
                    if (caracterActual == '=') {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //Voltar ao estado 0
                        estado = 0;
                        //Instanciar e retornando os valores 
                        saida = new Out();
                        saida.setLexema(palavra);
                        saida.setToken(Token.TOKEN_COMPACTO_DIVISAO_IGUAL);
                        saida.setLinha(i);
                        return saida;
                    } else if (caracterActual == '/') {
                        palavra = "";
                        //Ir ao estado 9
                        estado = 9;
                    } else if (caracterActual == '*') {
                        palavra = "";
                        //Ir ao estado 11
                        estado = 11;
                    } else {
                        //Voltar ao estado 0
                        estado = 0;
                        //Instanciar e retornando os valores 
                        saida = new Out();
                        saida.setLexema(palavra);
                        saida.setToken(Token.TOKEN_DIVISAO);
                        saida.setLinha(i);
                        return saida;
                    }
                    break;

                case 9:
                    if (caracterActual != '\n') {
                        //Voltar ao estado 0
                        estado = 9;
                    } else if (caracterActual == '\n') {
                        //Voltar ao estado 0
                        estado = 0;
                        //Acrescentar no Número de linhas
                        i++;
                    }
                    break;

                case 11:
                    if (caracterActual != '*' && caracterActual != '\n') {
                        //Ir ao estado 11
                        estado = 11;
                    } else if (caracterActual == '*') {
                        //Ir ao estado 12
                        estado = 12;
                    } else if (caracterActual == '\n') {
                        //Ir ao estado 11
                        estado = 11;
                        //Acrescentar no Número de linhas
                        i++;

                    }
                    break;

                case 12:
                    if (caracterActual == '*') {
                        //Ir ao estado 11
                        estado = 12;
                    } else if (caracterActual != '/') {
                        //Ir ao estado 11
                        estado = 11;
                    } else if (caracterActual == '/') {
                        //Voltar ao estado 0
                        estado = 0;
                    }
                    break;

                case 15:
                    if (isChar(caracterActual) || caracterActual == '_' || isDigito(caracterActual)) {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //Voltar ao estado 15
                        estado = 15;
                    } else if (caracterActual == '\n') {
                        //vai permanecer em 0
                        estado = 0;
                        i++;
                    } else {
                        //Função Pra poder retornar
                        voltar();
                        //Voltar ao estado 0
                        estado = 0;
                        //Instanciar e retornando os valores 
                        saida = new Out();
                        saida.setLexema(palavra);
                        saida.setToken(verificarPalavra(palavra));
                        saida.setLinha(i);
                        return saida;
                    }
                    break;

                case 17:
                    if (isDigito(caracterActual)) {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //Voltar ao estado 17
                        estado = 17;
                    } else if (caracterActual == '.') {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //Voltar ao estado 19
                        estado = 19;
                    } else {
                        //Função Pra poder retornar
                        voltar();
                        //Voltar ao estado 0
                        estado = 0;
                        //Instanciar e retornando os valores 
                        saida = new Out();
                        saida.setLexema(palavra);
                        saida.setToken(Token.TOKEN_DIGITO_INT);
                        saida.setLinha(i);
                        return saida;
                    }
                    break;

                case 19:
                    if (isDigito(caracterActual)) {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //Ir ao estado 20
                        estado = 20;
                    }
                    break;

                case 20:
                    if (isDigito(caracterActual)) {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //Voltar ao estado 20
                        estado = 20;
                    } else {
                        //Função Pra poder retornar
                        voltar();
                        //Voltar ao estado 0
                        estado = 0;
                        //Instanciar e retornando os valores 
                        saida = new Out();
                        saida.setLexema(palavra);
                        saida.setToken(Token.TOKEN_DIGITO_FLOAT);
                        saida.setLinha(i);
                        return saida;
                    }
                    break;

                case 70:
                    if (caracterActual != '"') {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //Voltar ao estado 70
                        estado = 70;
                    } else {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //Voltar ao estado 0
                        estado = 0;
                        //Instanciar e retornando os valores 
                        saida = new Out();
                        saida.setLexema(palavra);
                        saida.setToken(Token.TOKEN_STRING);
                        saida.setLinha(i);
                        return saida;
                    }
                    break;

                case 22:
                    //Voltar ao estado 0
                    estado = 0;
                    if (caracterActual == '=') {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //Instanciar e retornando os valores 
                        saida = new Out();
                        saida.setLexema(palavra);
                        saida.setToken(Token.TOKEN_DIFERENTE);
                        saida.setLinha(i);
                        return saida;
                    } else {
                        //Função Pra poder retornar
                        voltar();
                        //Instanciar e retornando os valores 
                        saida = new Out();
                        saida.setLexema(palavra);
                        saida.setToken(Token.TOKEN_NEGACAO);
                        saida.setLinha(i);
                        return saida;
                    }

                case 25:
                    //Voltar ao estado 0
                    estado = 0;
                    if (caracterActual == '=') {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //Instanciar e retornando os valores 
                        saida = new Out();
                        saida.setLexema(palavra);
                        saida.setToken(Token.TOKEN_CHAPEU_COMPACTO);
                        saida.setLinha(i);
                        return saida;
                    } else {
                        //Função Pra poder retornar
                        voltar();
                        //Instanciar e retornando os valores 
                        saida = new Out();
                        saida.setLexema(palavra);
                        saida.setToken(Token.TOKEN_CHAPEU);
                        saida.setLinha(i);
                        return saida;
                    }

                case 28:
                    if (caracterActual == '\'') {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //Voltar ao estado 0
                        estado = 0;
                        //Instanciar e retornando os valores 
                        saida = new Out();
                        saida.setLexema(palavra);
                        saida.setToken(Token.TOKEN_CARACTER);
                        saida.setLinha(i);
                        return saida;
                    } else {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //Voltar ao estado 28
                        estado = 28;
                    }
                    break;

                case 72:
                    //Voltar ao estado 0
                    estado = 0;
                    if (caracterActual == '=') {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //Instanciar e retornando os valores 
                        saida = new Out();
                        saida.setLexema(palavra);
                        saida.setToken(Token.TOKEN_IGUAL_LOGICO);
                        saida.setLinha(i);
                        return saida;
                    } else {
                        //Função Pra poder retornar
                        voltar();
                        //Instanciar e retornando os valores 
                        saida = new Out();
                        saida.setLexema(palavra);
                        saida.setToken(Token.TOKEN_IGUAL);
                        saida.setLinha(i);
                        return saida;
                    }

                case 49:
                    //Voltar ao estado 0
                    estado = 0;
                    if (caracterActual == '=') {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //Instanciar e retornando os valores 
                        saida = new Out();
                        saida.setLexema(palavra);
                        saida.setToken(Token.TOKEN_ASTERISCO_IGUAL);
                        saida.setLinha(i);
                        return saida;
                    } else {
                        //Função Pra poder retornar
                        voltar();
                        //Instanciar e retornando os valores 
                        saida = new Out();
                        saida.setLexema(palavra);
                        saida.setToken(Token.TOKEN_ASTERISCO);
                        saida.setLinha(i);
                        return saida;
                    }
                case 41:
                    //Voltar ao estado 0
                    estado = 0;
                    if (caracterActual == '=') {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //Instanciar e retornando os valores 
                        saida = new Out();
                        saida.setLexema(palavra);
                        saida.setToken(Token.TOKEN_MENOS_IGUAL);
                        saida.setLinha(i);
                        return saida;
                    } else if (caracterActual == '-') {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //Instanciar e retornando os valores 
                        saida = new Out();
                        saida.setLexema(palavra);
                        saida.setToken(Token.TOKEN_DECREMENTAL);
                        saida.setLinha(i);
                        return saida;
                    } else if (caracterActual == '>') {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //Instanciar e retornando os valores 
                        saida = new Out();
                        saida.setLexema(palavra);
                        saida.setToken(Token.TOKEN_MENOS_MAIOR);
                        saida.setLinha(i);
                        return saida;
                    } else {
                        //Função Pra poder retornar
                        voltar();
                        //Instanciar e retornando os valores 
                        saida = new Out();
                        saida.setLexema(palavra);
                        saida.setToken(Token.TOKEN_MENOS);
                        saida.setLinha(i);
                        return saida;
                    }

                case 46:
                    //Voltar ao estado 0
                    estado = 0;
                    if (caracterActual == '=') {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //Instanciar e retornando os valores 
                        saida = new Out();
                        saida.setLexema(palavra);
                        saida.setToken(Token.TOKEN_PERCENTAGEM_IGUAL);
                        saida.setLinha(i);
                        return saida;
                    } else {
                        //Função Pra poder retornar
                        voltar();
                        //Instanciar e retornando os valores 
                        saida = new Out();
                        saida.setLexema(palavra);
                        saida.setToken(Token.TOKEN_PERCENTAGEM);
                        saida.setLinha(i);
                        return saida;
                    }

                case 58:
                    //Voltar ao estado 0
                    estado = 0;
                    if (caracterActual == '=') {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //Instanciar e retornando os valores 
                        saida = new Out();
                        saida.setLexema(palavra);
                        saida.setToken(Token.TOKEN_E_COMERCIAL_COMPACTO);
                        saida.setLinha(i);
                        return saida;
                    } else if (caracterActual == '&') {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //Instanciar e retornando os valores 
                        saida = new Out();
                        saida.setLexema(palavra);
                        saida.setToken(Token.TOKEN_E_COMERCIAL_LOGICO);
                        saida.setLinha(i);
                        return saida;
                    } else {
                        //Função Pra poder retornar
                        voltar();
                        //Instanciar e retornando os valores 
                        saida = new Out();
                        saida.setLexema(palavra);
                        saida.setToken(Token.TOKEN_E_COMERCIAL);
                        saida.setLinha(i);
                        return saida;
                    }

                case 62:
                    //Voltar ao estado 0
                    estado = 0;
                    if (caracterActual == '=') {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //Instanciar e retornando os valores 
                        saida = new Out();
                        saida.setLexema(palavra);
                        saida.setToken(Token.TOKEN_BARRA_COMPACTO);
                        saida.setLinha(i);
                        return saida;
                    } else if (caracterActual == '|') {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //Instanciar e retornando os valores 
                        saida = new Out();
                        saida.setLexema(palavra);
                        saida.setToken(Token.TOKEN_OU);
                        saida.setLinha(i);
                        return saida;
                    } else {
                        //Função Pra poder retornar
                        voltar();
                        //Instanciar e retornando os valores 
                        saida = new Out();
                        saida.setLexema(palavra);
                        saida.setToken(Token.TOKEN_BARRA);
                        saida.setLinha(i);
                        return saida;
                    }

                case 37:
                    //Voltar ao estado 0
                    estado = 0;
                    if (caracterActual == '=') {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //Instanciar e retornando os valores 
                        saida = new Out();
                        saida.setLexema(palavra);
                        saida.setToken(Token.TOKEN_MAIS_COMPACTO);
                        saida.setLinha(i);
                        return saida;
                    } else if (caracterActual == '+') {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //Instanciar e retornando os valores 
                        saida = new Out();
                        saida.setLexema(palavra);
                        saida.setToken(Token.TOKEN_INCREMENTO);
                        saida.setLinha(i);
                        return saida;
                    } else {
                        //Função Pra poder retornar
                        voltar();
                        //Instanciar e retornando os valores 
                        saida = new Out();
                        saida.setLexema(palavra);
                        saida.setToken(Token.TOKEN_MAIS);
                        saida.setLinha(i);
                        return saida;
                    }

                case 31:

                    if (caracterActual == '=') {
                        //Voltar ao estado 0
                        estado = 0;
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //Instanciar e retornando os valores 
                        saida = new Out();
                        saida.setLexema(palavra);
                        saida.setToken(Token.TOKEN_MENOR_IGUAL);
                        saida.setLinha(i);
                        return saida;
                    } else if (caracterActual == '<') {
                        //Ir ao estado 34
                        estado = 34;
                        //Acrescentar a palavra
                        palavra += caracterActual;
                    } else {
                        //Voltar ao estado 0
                        estado = 0;
                        //Função Pra poder retornar
                        voltar();
                        //Instanciar e retornando os valores 
                        saida = new Out();
                        saida.setLexema(palavra);
                        saida.setToken(Token.TOKEN_MENOR);
                        saida.setLinha(i);
                        return saida;
                    }

                    break;

                case 34:
                    //Voltar ao estado 0
                    estado = 0;
                    if (caracterActual == '=') {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //Instanciar e retornando os valores 
                        saida = new Out();
                        saida.setLexema(palavra);
                        saida.setToken(Token.TOKEN_MENOR_MENOR_IGULA);
                        saida.setLinha(i);
                        return saida;
                    } else {
                        //Função Pra poder retornar
                        voltar();
                        //Instanciar e retornando os valores 
                        saida = new Out();
                        saida.setLexema(palavra);
                        saida.setToken(Token.TOKEN_MENOR_MENOR);
                        saida.setLinha(i);
                        return saida;
                    }

                case 52:

                    if (caracterActual == '=') {
                        //Voltar ao estado 0
                        estado = 0;
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //Instanciar e retornando os valores 
                        saida = new Out();
                        saida.setLexema(palavra);
                        saida.setToken(Token.TOKEN_MAIOR_IGUAL);
                        saida.setLinha(i);
                        return saida;
                    } else if (caracterActual == '>') {
                        //Ir ao estado 55
                        estado = 55;
                        //Acrescentar a palavra
                        palavra += caracterActual;
                    } else {
                        //Voltar ao estado 0
                        estado = 0;
                        //Função Pra poder retornar
                        voltar();
                        //Instanciar e retornando os valores 
                        saida = new Out();
                        saida.setLexema(palavra);
                        saida.setToken(Token.TOKEN_MAIOR);
                        saida.setLinha(i);
                        return saida;
                    }

                    break;

                case 55:
                    //Voltar ao estado 0
                    estado = 0;
                    if (caracterActual == '=') {
                        //Acrescentar a palavra
                        palavra += caracterActual;
                        //Instanciar e retornando os valores 
                        saida = new Out();
                        saida.setLexema(palavra);
                        saida.setToken(Token.TOKEN_MAIOR_MAIOR_IGUAL);
                        saida.setLinha(i);
                        return saida;
                    } else {
                        //Função Pra poder retornar
                        voltar();
                        //Instanciar e retornando os valores 
                        saida = new Out();
                        saida.setLexema(palavra);
                        saida.setToken(Token.TOKEN_MAIOR_MAIOR);
                        saida.setLinha(i);
                        return saida;
                    }

            }
        }

    }

    //Verificar se é uma palavra reservada ou não
    public Token verificarPalavra(String palavra) {
        if (palavrasReservadas.contains(palavra)) {
            if (palavra.equals("int")) {
                return Token.TOKEN_INT;
            } else if (palavra.equals("double")) {
                return Token.TOKEN_DOUBLE;
            } else if (palavra.equals("float")) {
                return Token.TOKEN_FLOAT;
            } else if (palavra.equals("char")) {
                return Token.TOKEN_CHAR;
            } else if (palavra.equals("continue")) {
                return Token.TOKEN_CONTINUE;
            } else if (palavra.equals("switch")) {
                return Token.TOKEN_SWITCH;
            } else if (palavra.equals("case")) {
                return Token.TOKEN_CASE;
            } else if (palavra.equals("break")) {
                return Token.TOKEN_BREAK;
            } else if (palavra.equals("while")) {
                return Token.TOKEN_WHILE;
            } else if (palavra.equals("long")) {
                return Token.TOKEN_LONG;
            } else if (palavra.equals("void")) {
                return Token.TOKEN_VOID;
            } else if (palavra.equals("return")) {
                return Token.TOKEN_RETURN;
            } else if (palavra.equals("for")) {
                return Token.TOKEN_FOR;
            } else if (palavra.equals("do")) {
                return Token.TOKEN_DO;
            } else if (palavra.equals("if")) {
                return Token.TOKEN_IF;
            } else if (palavra.equals("else")) {
                return Token.TOKEN_ELSE;
            } else if (palavra.equals("typedef")) {
                return Token.TOKEN_TYPEDEF;
            } else if (palavra.equals("struct")) {
                return Token.TOKEN_STRUCT;
            } else if (palavra.equals("_Bool")) {
                return Token.TOKEN__BOOL;
            } else if (palavra.equals("stdlib")) {
                return Token.TOKEN_STDLIB_H;
            } else if (palavra.equals("stdio")) {
                return Token.TOKEN_STDIO_H;
            } else if (palavra.equals("#include")) {
                return Token.TOKEN_INCLUDE;
            } else if (palavra.equals("#define")) {
                return Token.TOKEN_DEFINE;
            }
        }
        return Token.TOKEN_ID;

    }

}
