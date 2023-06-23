package POO.trabalhopraticoPOO;

import java.time.LocalDate;

/**
 * A classe Filme representa um filme, que é uma subclasse de Midia.
 * Ela contém informações específicas de filmes, como a duração.
 */
public class Filme extends Midia {
    private int duracao; 

    /**
     * Construtor vazio da classe Filme.
     */
    public Filme() {}

    /**
     * Construtor da classe Filme que recebe os parâmetros necessários para criar um objeto Filme.
     * @param idMidia O ID da mídia.
     * @param nome O nome do filme.
     * @param dataLancamento A data de lançamento do filme.
     * @param duracao A duração do filme em minutos.
     */
    public Filme(String idMidia, String nome, String dataLancamento, String duracao) {
        super(idMidia, nome, dataLancamento);
        
        try {
            this.duracao = Integer.parseInt(duracao);
        } catch (NumberFormatException e) {
            System.out.printf("Erro ao converter. ", duracao);
        }
    }

    /**
     * Construtor da classe Filme utilizado para criar um objeto Filme a partir de dados numéricos.
     * @param parseInt O ID da mídia.
     * @param string O nome do filme.
     * @param parse A data de lançamento do filme.
     * @param parseInt2 A duração do filme em minutos.
     */
    public Filme(int parseInt, String string, LocalDate parse, int parseInt2) {
        // Implementação do construtor não fornecida
    }

    /**
     * Converte uma string de dados em um objeto Filme.
     * @param dados A string contendo os dados do filme no formato "idMidia;nome;dataLancamento;duracao".
     * @return Um objeto Filme com os dados fornecidos.
     */
    @Override
    public ConversorParaString converterParaObjeto(String dados) {
        String[] valores = dados.split(";");
        return new Filme(valores[0], valores[1], valores[2], valores[3]);
    }}

    
