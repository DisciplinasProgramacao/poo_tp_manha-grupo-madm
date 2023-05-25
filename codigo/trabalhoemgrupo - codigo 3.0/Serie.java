package POO.trabalhoemgrupo;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Serie extends midia {
    // início dos atributos
    private int episodios;
    private Map<Integer, Serie> series;

    // fim dos atributos

    /**
     * inicializador Serie que configura a visualização e:
     * 
     * @param nome
     * @param genero
     * @param idioma
     */
    public Serie(int episodios, int id, String nome, String idioma, String genero, String comentario, int visualizacao,
            int avaliacao, LocalDate dataLancamento) {
        super(id, nome, idioma, genero, comentario, visualizacao, avaliacao, dataLancamento);
        this.episodios = episodios;
        this.series = new HashMap<>(80000, 0.8f);

    }

    /**
     * método de registrar a visualização
     */
    public void registrarVisualizacao() {
        this.visualizacao++;
    }

    /**
     * método para ler o arquivo de séries, acessando via hash map o arquivo
     * 
     * @return as séries mostradas
     * @throws FileNotFoundException
     */
    public Map<Integer, Serie> lerSeries() throws FileNotFoundException {
        Map<Integer, Serie> series = new HashMap<>();
        File file = new File("C:/trabalhoemgrupo/banco/POO_Series.csv");
        Scanner entrada = new Scanner(file);

        while (entrada.hasNextLine()) {
            String[] campos = entrada.nextLine().split(";");
            int idSerie = Integer.parseInt(campos[0]);
            String nome = campos[1];
            LocalDate dataLancamento = LocalDate.parse(campos[2]);
            String genero = campos[3];
            String idioma = campos[4];
            Serie serie = new Serie(episodios, id, nome, idioma, genero, comentario, visualizacao, avaliacao,
                    dataLancamento);
            series.put(idSerie, serie);
        }

        entrada.close();
        return series;
    }

    public void comentarista() {

    }

}
