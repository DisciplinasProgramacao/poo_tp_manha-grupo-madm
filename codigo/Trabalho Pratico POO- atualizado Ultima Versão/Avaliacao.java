package POO.trabalhopraticoPOO;

public class Avaliacao {
    private int nota;         // Atributo para armazenar a nota da avaliação
    private String comentario; // Atributo para armazenar o comentário da avaliação

    /**
     * Construtor da classe Avaliacao
     * @param nota A nota da avaliação
     */
    public Avaliacao(int nota) {
        this.nota = nota;
    }

    /**
     * Adiciona um comentário à avaliação
     * @param comentario O comentário a ser adicionado
     */
    public void addComentario(String comentario) {
        this.comentario = comentario;
    }
    
    /**
     * Retorna a nota desta avaliação
     * @return A nota da avaliação
     */
    public int getNota() {
        return nota;
    }

    /**
     * Retorna o comentário desta avaliação
     * @return O comentário da avaliação
     */
    public String getComentario() {
        return comentario;
    }
}
