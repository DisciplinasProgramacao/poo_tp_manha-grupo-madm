package POO.trabalhopraticoPOO;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Classe abstrata que representa uma mídia, como filmes ou séries.
 * Implementa a interface IStringConverter para conversão de objetos em strings e vice-versa.
 */

public abstract class Midia implements ConversorParaString {
    
	private String idMidia;
	private String nome;
	private String genero;
	private ArrayList<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();
	private String idioma;
	private int contReproducoes = 0;
	private LocalDate dataLancamento;
	private double avaliacaoMedia;
	private int soma;
	private boolean isLancamento;

    /**
     * Construtor padrão da classe Midia.
     */
	public Midia() {}

 /**
     * Construtor da classe Midia.
     *
     * @param idMidia         ID da mídia.
     * @param nome            Nome da mídia.
     * @param dataLancamento  Data de lançamento da mídia no formato de string.
     */
	public Midia(String idMidia, String nome, String dataLancamento) {
		this.idMidia = idMidia;
		this.nome = nome;
		this.dataLancamento = Utilitario.converteParaData(dataLancamento);
	}

	/**
	 * Este método calcula a média das avaliações de uma mídia
	 */
	public void calculaMediaAvaliacoes()
	{
		this.soma = this.avaliacoes.stream().mapToInt(i -> i.getNota()).sum();
		if(this.avaliacoes.size() != 0) {
			this.avaliacaoMedia = soma/this.avaliacoes.size();
		}
	}

	   /**
     * Obtém o ID da mídia.
     *
     * @return O ID da mídia.
     */
    public String getID() {
        return idMidia;
    }

    /**
     * Adiciona uma avaliação à lista de avaliações da mídia.
     *
     * @param avaliacao A avaliação a ser adicionada.
     */
    public void avaliarMidia(Avaliacao avaliacao) {
        this.avaliacoes.add(avaliacao);
    }

    /**
     * Obtém o nome da mídia.
     *
     * @return O nome da mídia.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome da mídia.
     *
     * @param nome O nome da mídia.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o idioma da mídia.
     *
     * @return O idioma da mídia.
     */
    public String getIdioma() {
        return idioma;
    }

    /**
     * Define o idioma da mídia.
     *
     * @param idioma O idioma da mídia.
     */
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    /**
     * Incrementa o contador de reproduções da mídia.
     */
    public void incrementarReproducoes() {
        this.contReproducoes += 1;
    }

    /**
     * Converte uma string para um objeto do tipo da classe que estende Midia.
     *
     * @param dados A string contendo os dados a serem convertidos.
     * @return O objeto convertido.
     */
    public abstract ConversorParaString converterParaObjeto(String dados);

    /**
     * Obtém a chave da mídia.
     *
     * @return A chave da mídia.
     */
    @Override
    public String getChave() {
        return this.nome;
    }

    /**
     * Obtém a média das avaliações desta mídia.
     *
     * @return A média das avaliações.
     */
    public double getAvaliacaoMedia() {
        return avaliacaoMedia;
    }

    /**
     * Verifica se a mídia é um lançamento.
     *
     * @return true se for um lançamento, false caso contrário.
     */
    public boolean isLancamento() {
        return isLancamento;
    }

    /**
     * Obtém o número de visualizações da mídia.
     *
     * @return O número de visualizações da mídia.
     */
    public int getVisualizacoes() {
        return this.contReproducoes;
    }

    /**
     * Obtém o gênero da mídia.
     *
     * @return O gênero da mídia.
     */
    public String getGenero() {
        return genero;
    }
}