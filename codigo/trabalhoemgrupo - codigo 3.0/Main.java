package POO.trabalhoemgrupo;

import java.util.Scanner;
import java.io.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // início dos atributos
        Scanner entrada = new Scanner(System.in);
        char a;
        // fim dos atributos
        System.out.println("Qual banco de arquivos você quer ver? 1-séries 2-espectadores 3-audiência");
        // scanner para escolha de qual banco de dados deseja acessar
        a = entrada.next().charAt(0);

        // switch para escolha de arquivos
        switch (a) {
            case '1':

                try {
                    Serie tempSerie = new Serie(a, a, null, null, null, null, a, a, null);
                    Map<Integer, Serie> series = (Map<Integer, Serie>) tempSerie.lerSeries();

                    // Exemplo de iteração sobre as séries lidas
                    for (Serie s : series.values()) {
                        System.out.println("Nome: " + s.nome);
                        System.out.println("Gênero: " + s.genero);
                        System.out.println("Idioma: " + s.idioma);
                        System.out.println("Visualização: " + s.visualizacao);
                        System.out.println("ID da Série: " + s.id);
                        System.out.println("Data de Lançamento: " + s.dataLancamento);
                        System.out.println();
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("Erro ao ler o arquivo de séries: " + e.getMessage());
                }

                break;
        }
    }

}

// se o ler series der certo, fazer para audiência e espectador
