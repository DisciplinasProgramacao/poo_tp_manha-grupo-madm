package POO.trabalhoemgrupo;

import java.sql.Date;
import java.time.LocalDate;

public class midia {
    protected int id;
    protected String nome;
    protected String idioma;
    protected String genero;
    protected String comentario;
    protected int visualizacao;
    protected int avaliacao;
    protected Date dataLancamento;

    public midia(int idSerie, String nome, String idioma, String genero, String comentario, int visualizacao,
            int avaliacao,
            LocalDate dataLancamento2) {
        this.id = id;
        this.nome = nome;
        this.idioma = idioma;
        this.genero = genero;
        this.comentario = comentario;
        this.visualizacao = visualizacao;
        this.avaliacao = avaliacao;
        this.dataLancamento = dataLancamento;
    }

    public void registrarVisualizacao() {

    }

    public void comentarista() {

    }

}
