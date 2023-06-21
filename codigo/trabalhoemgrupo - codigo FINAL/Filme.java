package POO.trabalhoemgrupo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Filme extends Midia {
    // Início dos atributos
    private int duracao;
    private List<Cliente> comentarios;
    // Fim dos atributos

    /**
     * construtor padrão da classe filme com as informações que se devem ter,
     * implementando as da mídia
     * 
     * @param duracao
     * @param id
     * @param nome
     * @param idioma
     * @param genero
     * @param comentario
     * @param visualizacao
     * @param notas
     * @param dataLancamento
     */
    public Filme(int duracao, int id, String nome, String idioma, List<String> genero, String comentario,
            int visualizacao, List<Integer> notas, LocalDate dataLancamento) {
        super(id, nome, idioma, genero, comentario, visualizacao, notas, dataLancamento);
        this.duracao = duracao;
    }

    /**
     * construtor para inicializar as variáveis e o super
     * 
     * @param id
     * @param nome
     * @param dataLancamento
     * @param duracao
     */
    public Filme(int id, String nome, LocalDate dataLancamento, int duracao) {
        super(id, nome, "", null, "", 0, new ArrayList<>(), dataLancamento);
        this.duracao = duracao;
    }

    // Construtor vazio para permitir a criação de um objeto Filme sem parâmetros
    public Filme() {
        super();
    }

    // método override para registrar visualização no filme
    @Override
    public int registrarVisualizacao() {
        return ++visualizacao;
    }

    // método override para adicionar comentário do cliente a lista de comentários
    @Override
    public void comentar(String comentario) {
        Cliente novoComentario = new Cliente(comentario);
        comentarios.add(novoComentario);

    }

    // método override para get audiencia
    @Override
    public int getAudiencia() {
        return 0;
    }

    // método override para calcular a nota média de um filme, usando stream
    @Override
    public double calcularNotaMedia() {
        return this.notas.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);
    }

    // método override para formatar o filme no arquivo CSV
    @Override
    public String Formatador() {
        return String.format("%d;%s;%s;%d",
                id, nome, dataLancamento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), duracao);
    }

}
