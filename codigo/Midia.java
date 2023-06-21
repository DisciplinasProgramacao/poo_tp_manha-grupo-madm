package POO.trabalhoemgrupo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public abstract class Midia {
    // início dos atributos
    protected int id;
    protected String nome;
    protected String idioma;
    protected List<Genero> genero;
    protected String comentario;
    protected int visualizacao = 0;
    protected List<Integer> notas;
    protected LocalDate dataLancamento;
    // fim dos atributos

    /**
     * construtor mídia, com informações que devem estar presentes em uma mídia
     * inicializadas
     * 
     * @param id
     * @param nome
     * @param idioma
     * @param genero
     * @param comentario
     * @param visualizacao
     * @param notas
     * @param dataLancamento
     */
    public Midia(int id, String nome, String idioma, List genero, String comentario, int visualizacao,
            List<Integer> notas, LocalDate dataLancamento) {
        this.id = id;
        this.nome = nome;
        this.idioma = idioma;
        this.genero = genero;
        this.comentario = comentario;
        this.visualizacao = visualizacao;
        this.notas = notas;
        this.dataLancamento = dataLancamento;
    }

    // construtor vazio
    public Midia() {
    }

    /**
     * método para adicionar avaliação, passando a lista notas o id do cliente que
     * avaliou e a nota
     * 
     * @param idCliente
     * @param nota
     */
    public void adicionarAvaliacao(String idCliente, int nota) {
        notas.add(nota);
    }

    /**
     * método int para registrar visualização para a mídia
     * 
     * @return
     */
    public int registrarVisualizacao() {

        return visualizacao++;
    }

    /**
     * método abstrato para audiência de cada mídia
     * 
     * @return
     */
    public abstract int getAudiencia();

    /**
     * método para comentar
     * 
     * @param comentario
     */
    public void comentar(String comentario) {

    }

    /**
     * método lista para get de genero da mídia
     * 
     * @return gênero
     */
    public List<Genero> getGenero() {
        return genero;
    }

    /**
     * get para idioma da mídia da lista
     * 
     * @return idioma
     */
    public String getIdioma() {
        return idioma;
    }

    /**
     * get para o nome
     * 
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     * get para o id
     * 
     * @return id
     */
    public int getId() {
        return this.id;
    }

    /**
     * método para get avaliação média
     * 
     * @return
     */
    public double getMediaAvaliacao() {
        return 0;
    }

    /**
     * 
     * compara este objeto Midia com outro objeto para verificar igualdade.
     * 
     * @param obj o objeto a ser comparado.
     * @return true se os objetos forem iguais, false caso contrário.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Midia other = (Midia) obj;
        return id == other.id &&
                Objects.equals(nome, other.nome) &&
                Objects.equals(idioma, other.idioma) &&
                Objects.equals(genero, other.genero);
    }

    /**
     * get avaliadores da mídia
     * 
     * @return
     */
    public Collection<Midia> getAvaliadores() {
        return null;
    }

    // método abstrato para calcular nota média
    public abstract double calcularNotaMedia();

    // método abstrato para formatar cada mídia de modos diferentes
    public abstract String Formatador();

    // toString para aparecer na tela no formato correto
    @Override
    public String toString() {
        return "\nNome: " + nome
                + "\nID: " + id
                + "\nData de Lançamento: " + dataLancamento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

}