package POO.trabalhoemgrupo;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;

public class Serie {
    //início dos atributos
    protected String nome;
    protected String genero;
    protected String idioma;
    protected int visualizacao;
    //fim dos atributos
    /**
     * inicializador Serie que configura a visualização e:
     * @param nome
     * @param genero
     * @param idioma
     */
    public Serie(String nome, String genero, String idioma) {
        this.nome = nome;
        this.genero = genero;
        this.idioma = idioma;
        this.visualizacao = 0;
    }

    public Serie(String login, char fa, int idSerie) {
    }

    public Serie(int id, String nome2, LocalDate dataLancamento) {
    }
    /**
     * método de registrar a visualização
     */
    public void registrarVisualizacao() {
        this.visualizacao++;
    }
    /**
     * método que lê o arquivo de séries e caracteriiza o nome e a data de lançamento
     * @param caminhoArquivo
     * @return as séries do arquivo
     * @throws IOException
     */
    public static List<Serie> lerSeries(String caminhoArquivo) throws IOException {
        List<Serie> series = new ArrayList<>();
        BufferedReader leitor = new BufferedReader(new FileReader("C:/trabalhoemgrupo/POO_Series.csv"));
        String linha = leitor.readLine(); // lê a primeira linha (cabeçalho)
        while ((linha = leitor.readLine()) != null) {
            String[] campos = linha.split(";");
            int id = Integer.parseInt(campos[0]);
            String nome = campos[1];
            LocalDate dataLancamento = LocalDate.parse(campos[2]);
            series.add(new Serie(id, nome, dataLancamento));
        }
        leitor.close();
        return series;
    }
    
    
}


