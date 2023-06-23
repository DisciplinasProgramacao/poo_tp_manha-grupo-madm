package POO.trabalhopraticoPOO;

import java.time.LocalDate;

/**
 * Classe que representa uma série, que é um tipo de mídia.
 */
public class Serie extends Midia {

    private int quantEpisodios;

    /**
     * Construtor padrão da classe `Serie`.
     */
    public Serie() {
    }

    /**
     * Construtor da classe `Serie` que recebe o ID da mídia, o nome e a data de lançamento da série.
     *
     * @param idMidia       O ID da mídia.
     * @param nome          O nome da série.
     * @param dataLancamento A data de lançamento da série.
     */
    public Serie(String idMidia, String nome, String dataLancamento) {
        super(idMidia, nome, dataLancamento);
    }

    /**
     * Construtor da classe `Serie` que recebe informações inteiras, porém os parâmetros não são usados.
     *
     * @param parseInt Valor inteiro não utilizado.
     * @param string    Valor string não utilizado.
     * @param parse    Valor LocalDate não utilizado.
     * @param i         Valor inteiro não utilizado.
     */
    public Serie(int parseInt, String string, LocalDate parse, int i) {
    }

    /**
     * Converte uma string em um objeto do tipo `Serie`.
     *
     * @param dados A string contendo os dados a serem convertidos.
     * @return O objeto `Serie` convertido.
     */
    @Override
    public ConversorParaString converterParaObjeto(String dados) {
        String[] valores = dados.split(";");
        return new Serie(valores[0], valores[1], valores[2]);
    }
}

