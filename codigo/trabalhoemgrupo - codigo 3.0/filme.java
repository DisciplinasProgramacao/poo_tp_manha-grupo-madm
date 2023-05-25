package POO.trabalhoemgrupo;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class filme extends midia {
    private int duracao;

    // construtor
    public filme(int duracao, int id, String nome, String idioma, String genero, String comentario, int visualizacao,
            int avaliacao, LocalDate dataLancamento) {
        super(id, nome, idioma, genero, comentario, visualizacao, avaliacao, dataLancamento);
        this.duracao = duracao;
    }

    public Map<Integer, filme> catalogoFilmes() throws IOException {
        Map<Integer, filme> filmes = new HashMap<>();
        File file = new File("banco/POO_Filmes.csv");
        Scanner entrada = new Scanner(file);

        while (entrada.hasNextLine()) {
            String[] campos = entrada.nextLine().split(";");
            int idSerie = Integer.parseInt(campos[0]);
            String nome = campos[1];
            LocalDate dataLancamento = LocalDate.parse(campos[2]);
            String genero = campos[3];
            String idioma = campos[4];

            filme filme = new filme(duracao, id, nome, idioma, genero, comentario, visualizacao, avaliacao,
                    dataLancamento);
            filmes.put(idSerie, filme);
        }

        entrada.close();
        return filmes;
    }

    public void registrarVisualizacao() {
        this.visualizacao++;
    }

    public void comentarista() {

    }

}
