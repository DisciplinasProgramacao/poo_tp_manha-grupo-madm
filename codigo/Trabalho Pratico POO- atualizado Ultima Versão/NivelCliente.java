package POO.trabalhopraticoPOO;

public enum NivelCliente {
    // atributos enum, caracterizando as categorias do streaming e o que eles podem
    // fazer
    REGULAR(false, false),
    ESPECIALISTA(true, false),
    PROFISSIONAL(true, true);

    // início dos atributos
    private boolean podeComentar;
    private boolean podeAssistir;
    // fim dos atributos

    /**
     * construtor nivelCliente
     * 
     * @param podeComentar boolean para poder comentar
     * @param podeAssistir boolean para poder assistir conteúdos extras
     */
    private NivelCliente(boolean podeComentar, boolean podeAssistir) {
        this.podeComentar = podeComentar;
        this.podeAssistir = podeAssistir;
    }

    /**
     * método para retornar se o cliente pode comentar
     * 
     * @return
     */
    public boolean podeComentar() {
        return podeComentar;
    }

    /**
     * boolean para retornar se o cliente pode assistir extras
     * 
     * @return
     */
    public boolean podeAssistir() {
        return podeAssistir;
    }

    /**
     * método para validar nível do cliente
     * 
     * @param podeComentar se o cliente pode comentar
     * @param podeAssistir se o cliente pode assistir extras
     * @return nível do cliente
     */
    public static NivelCliente meuNivel(boolean podeComentar, boolean podeAssistir) {
        for (NivelCliente nivel : NivelCliente.values()) {
            if (nivel.podeComentar == podeComentar && nivel.podeAssistir == podeAssistir) {
                return nivel;
            }
        }
        return null;
    }
}
