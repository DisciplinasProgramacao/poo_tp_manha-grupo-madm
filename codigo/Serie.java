package POO.trabalhoemgrupo;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Serie extends Midia {
    // início dos atributos
    private int quantidadeEpisodios;
    private List<Cliente> comentarios;

    /**
     * construtor com as informações que uma série deve ter, implementando as da
     * mídia
     * 
     * @param episodios
     * @param id
     * @param nome
     * @param idioma
     * @param genero
     * @param comentario
     * @param visualizacao
     * @param notas
     * @param dataLancamento
     */
    public Serie(int episodios, int id, String nome, String idioma, List<String> genero, String comentario,
            int visualizacao, List<Integer> notas, LocalDate dataLancamento) {
        super(id, nome, idioma, genero, comentario, visualizacao, notas, dataLancamento);
        this.quantidadeEpisodios = episodios;
    }

    /**
     * construtor da classe Serie
     * 
     * @param id
     * @param nome
     * @param dataLancamento
     * @param qtdEpisodios
     */
    public Serie(int id, String nome, LocalDate dataLancamento, int qtdEpisodios) {
        super(id, nome, nome, new ArrayList<>(), nome, 0, new ArrayList<>(), dataLancamento);
        this.quantidadeEpisodios = qtdEpisodios;
    }

    /**
     * setar quantidade de episódios de uma série
     * 
     * @param quantidadeEpisodios
     */
    public void setQuantidadeEpisodios(int quantidadeEpisodios) {
        if (quantidadeEpisodios < 1) {
            throw new InvalidParameterException("A quantidade de episódios não pode ser menor que 1!");
        }
        this.quantidadeEpisodios = quantidadeEpisodios;
    }

    // método override de registrar visualização
    @Override
    public int registrarVisualizacao() {
        return ++visualizacao; // Incrementa e retorna o novo valor de visualização
    }

    // método override para comentar
    @Override
    public void comentar(String comentario) {
        Cliente novoComentario = new Cliente(comentario);
        comentarios.add(novoComentario);

    }

    /**
     * get para data de lançamento
     * 
     * @return
     */
    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    /**
     * get para quantidade de episodios
     * 
     * @return quantidade de episodios
     */
    public int getQtdEpisodios() {
        return quantidadeEpisodios;
    }

    // override com get audiencia
    @Override
    public int getAudiencia() {
        return 0;
    }

    // método override para calcular nota média
    @Override
    public double calcularNotaMedia() {
        return this.notas.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);
    }

    /**
     * formata os dados da série em formato CSV
     */
    @Override
    public String Formatador() {
        return String.format("%d;%s;%s;%d",
                id, nome, dataLancamento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), quantidadeEpisodios);
    }
}
