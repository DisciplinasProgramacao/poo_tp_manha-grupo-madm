package POO.trabalhopraticoPOO;

/**
 * Interface para conversão entre objetos e strings.
 */
public interface ConversorParaString {
    
    /**
     * Converte uma string em um objeto do tipo implementado pela classe que implementa a interface.
     *
     * @param dados A string a ser convertida em objeto.
     * @return O objeto convertido a partir da string.
     */
    public ConversorParaString converterParaObjeto(String dados);
    
    /**
     * Obtém a chave associada ao objeto.
     *
     * @return A chave do objeto como uma string.
     */
    public String getChave();
}
